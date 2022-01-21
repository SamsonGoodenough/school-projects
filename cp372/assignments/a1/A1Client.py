# Import socket module
from socket import * 
import sys # In order to terminate the program
import struct

# constants
HEADER_SIZE = 8

print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

serverName = 'localhost'
# Assign a port number
serverPort = 12000

# Bind the socket to server address and server port
clientSocket = socket(AF_INET, SOCK_DGRAM)
clientSocket.settimeout(0.5)

# Create a message
entity = 1 # client
pcode = 0 # phase 1
data = 'Hello World!!!'
data_length = len(data)

format = f'!IHH{data_length}s'
packet = struct.pack(format, data_length, pcode, entity, data.encode())

clientSocket.sendto(packet, (serverName, serverPort))
response, serverAddress = clientSocket.recvfrom(2048)
unpacked = struct.unpack(f'!HHIIHH', response)
print(f'The packet received from {serverAddress} is: {unpacked}')
serverPort = unpacked[3]

print('-'*15, 'Phase B', '-'*15)
pcode = unpacked[-1]
repeat = unpacked[2]
len = unpacked[4]
packet_id = 0

while packet_id < repeat:
  try:
    # send packet
    data = bytearray(len + len%4)
    data_length = len + len%4
    format = f'!IHHI{data_length}s'
    packet = struct.pack(format, data_length, pcode, entity, packet_id, data)

    clientSocket.sendto(packet, (serverName, serverPort))
    
    # wait for response
    acknowledgment, serverAddress = clientSocket.recvfrom(2048)
    unpacked = struct.unpack(f'!IHHI', acknowledgment)
    print(f'The packet received from {serverAddress} is: {unpacked}')
    
    packet_id += 1
  except:
    print('\033[93m','='*5,'Timeout: trying again.','='*5,'\033[0m')

clientSocket.close()

sys.exit() # Terminate the program after sending the corresponding data