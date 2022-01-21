# Import socket module
import random
import struct
from socket import * 
import sys
from time import sleep # In order to terminate the program

print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

# constants
HEADER_SIZE = 8

# Assign a port number
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_DGRAM)

# Bind the socket to server address and server port
serverSocket.bind(("", serverPort))

print('The server is ready to receive')

packet, clientAddress = serverSocket.recvfrom(1024)
unpacked = struct.unpack(f'!IHH{len(packet) - HEADER_SIZE}s', packet)

# sTODO verify the packet
print(f'The packet received from {clientAddress} is: {unpacked}')
print(f'\tThe data is: {unpacked[-1].decode()}')
if unpacked[-1].decode() == 'Hello World!!!':
  print('\tThe packet is correct')

# response
entity = 2 # server
pcode = 0 # phase 1
repeat = random.randint(5, 20)
udp_port = random.randint(20000, 30000)
len = random.randint(50, 100)
codeA = random.randint(100, 400)

# make packet
response = struct.pack('!HHIIHH', pcode, entity, repeat, udp_port, len, codeA)
serverSocket.sendto(response, clientAddress)

# kill the socket
serverSocket.close()

# listen to new port
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(("", udp_port))
serverSocket.settimeout(3)
print(f'The server is ready to receive on port {udp_port}')

print('-'*15, 'Phase B', '-'*15)
data_length = 4
i = 0
while i < repeat:
  # wait for packet
  packet, clientAddress = serverSocket.recvfrom(1024)
  if random.random() > (1/5):
    format = f'!IHHI{len+len%4}s'
    unpacked = struct.unpack(format, packet)
    unpacked_data = unpacked[-1].replace(b"\x00", b"0")
    print(f'Packet received from {clientAddress}\n\tpacket is: \t{unpacked[:-1]}\n\tdata is: \t0*{len+len%4}')
    #sTODO verify the packet
    
    # send acknowledgment
    packet_id = unpacked[3]
    data_length = 4
    acknowledgment = struct.pack('!IHHI', data_length, pcode, entity, packet_id)
    serverSocket.sendto(acknowledgment, clientAddress)
    i += 1
  else:
    print('\033[93m','='*5,'Warning: Packet Dropped!','='*5,'\033[0m')
  

sys.exit() # Terminate the program after sending the corresponding data