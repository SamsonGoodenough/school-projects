"""
  Assignment 1: Client
    1/26/2022 | cp372
  
  Group Members:
    Riley Huston (190954880)
    hust4880@mylaurier.ca
    Samson Goodenough (190723380)
    good3380@mylaurier.ca
"""

# def check_server_response(response):
#   data_len, pcode, entity = struct.unpack_from('!IHH', response)
#   print(data_len, pcode, entity)
#   if pcode==555:
#     response = response[8:]
#     print(response.decode())
#     sys.exit()
#   return

# Import socket module
from random import random
from socket import * 
import sys # In order to terminate the program
import struct
from time import sleep

# constants
HEADER_SIZE = 8
ENTITY = 1 # client

def printError(error):
  print('\033[91m','='*5,'ERROR:',error,'='*5,'\033[0m')
  
def padding(length):
  return 0 if (4-length%4 == 4) else 4-length%4

print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

serverName = '34.69.60.253'
#serverName = 'localhost'
# Assign a port number
serverPort = 12000

# Bind the socket to server address and server port
clientSocket = socket(AF_INET, SOCK_DGRAM)
clientSocket.settimeout(1)

# Create a message
pcode = 0 # phase 1
data = 'Hello World!!!'
data = data.encode() + bytearray(padding(len(data)))
data_length = len(data) 

# Send Phase A Client Packet
format = f'!IHH{data_length}s'
packet = struct.pack(format, data_length, pcode, ENTITY, data)

clientSocket.sendto(packet, (serverName, serverPort))

# Receive Phase A Server Packet
response, serverAddress = clientSocket.recvfrom(2048)

if(response == b'-1'): # packet error
  printError("PACKET NOT VALID")
  clientSocket.close()
  sys.exit()

data_length, pcode, server_entity, repeat, udp_port, len1, codeA = struct.unpack(f'!IHHIIHH', response)
print(f'The packet received from {serverAddress}\n\tpacket is: {(data_length, pcode, server_entity, repeat, udp_port, len1, codeA)}')

# Change pcode to next phase
pcode = codeA
print('-'*15, 'End of Phase A', '-'*15)
print('-'*15, 'Phase B', '-'*15)
packet_id = 0

while packet_id < repeat:
  try:
    # Send Phase B Client Packet

    data = bytearray(len1 + padding(len1))
    data_length = len(data) + 4 # add 4 for packet_id (int)

    format = f'!IHHI{data_length}s'
    packet = struct.pack(format, data_length, pcode, ENTITY, packet_id, data)

    print(f'SENDING TO {serverAddress}: ({data_length, pcode, ENTITY, packet_id} ByteArray of length: {len(data)})')
    clientSocket.sendto(packet, (serverName, udp_port))
    
    # wait for response
    # Receive Phase B-1 Server Packet
    acknowledgment, serverAddress = clientSocket.recvfrom(2048)

    if(acknowledgment == b'-1'): # packet error
      printError("PACKET NOT VALID")
      clientSocket.close()
      sys.exit()

    data_length, pcode, entity, _packet_id = struct.unpack(f'!IHHI', acknowledgment)
    print(f'ACK FROM {serverAddress}: {(data_length, pcode, entity, _packet_id)}')
    packet_id += 1

  except Exception as e:
    print('\033[93m','='*5,'Timeout: trying again.','='*5,'\033[0m')
    #print(e)

# Receive Phase B-2 Server Packet
response, serverAddress = clientSocket.recvfrom(2048)

if(response == b'-1'): # packet error
  printError("PACKET NOT VALID")
  clientSocket.close()
  sys.exit()
data_length, pcode, entity, tcp_port, codeB = struct.unpack('!IHHII', response)

# Change pcode to next phase
pcode = codeB

clientSocket.close()

print('-'*15, 'End of Phase B', '-'*15)
# phase C
print('-'*15, 'Phase C', '-'*15)
print('Sleeping for 3 seconds...')


# Create new TCP socket
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.settimeout(0.5)

sleep(3) # wait for server to start

# Connect to TCP Server Socket
clientSocket.connect((serverName, tcp_port))

# Receive Phase C Server Packet
response = clientSocket.recv(1024)

if(response == b'-1'): # packet error
    printError("PACKET NOT VALID")
    clientSocket.close()
    sys.exit()

data_length, pcode, server_entity, repeat2, len2, codeC, char = struct.unpack('!IHHIIIc', response)
print(f'The packet received from {serverAddress} is: {(data_length, pcode, server_entity, repeat2, len2, codeC, char)}')

# Change pcode to next phase
pcode = codeC


print('-'*15, 'End of Phase C', '-'*15)
# phase D
print('-'*15, 'Phase D', '-'*15)

data_length = len2 + padding(len2)
pcode = codeC
data = char*data_length

for i in range(repeat2):
  # Send Phase D Client Packet
  packet = struct.pack(f'!IHH{data_length}s', data_length, pcode, ENTITY, data)
  print(i,f"| SENDING TO {serverAddress}:", data_length, pcode, ENTITY, data)
  clientSocket.send(packet)
  sleep(0.5)

# Receive Phase D Server Packet
response = clientSocket.recv(2048)

if(response == b'-1'): # packet error
  printError("PACKET NOT VALID")
  clientSocket.close()
  sys.exit()

data_length, pcode, entity, codeD = struct.unpack(f'!IHHI', response)
print(f'The packet received from {serverAddress} is: {(data_length, pcode, entity, codeD)}')

# Change pcode to next phase
pcode = codeD
print('-'*15, 'End of Phase D', '-'*15)

clientSocket.close()

sys.exit() # Terminate the program after sending the corresponding data