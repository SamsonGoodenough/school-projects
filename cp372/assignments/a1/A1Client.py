"""
  Assignment 1: Client
    1/26/2022 | cp372
  
  Group Members:
    Riley Huston (190954880)
    hust4880@mylaurier.ca
    Samson Goodenough (190723380)
    good3380@mylaurier.ca
"""

# Import socket module
from random import random
from socket import * 
import sys # In order to terminate the program
import struct
from time import sleep

# constants
HEADER_SIZE = 8
ENTITY = 1 # client

print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

serverName = 'localhost'
# Assign a port number
serverPort = 12000

# Bind the socket to server address and server port
clientSocket = socket(AF_INET, SOCK_DGRAM)
clientSocket.settimeout(0.5)

# Create a message
pcode = 0 # phase 1
data = 'Hello World!!!'
data_length = len(data)

# Send Phase A Client Packet
format = f'!IHH{data_length}s'
packet = struct.pack(format, data_length, pcode, ENTITY, data.encode())

clientSocket.sendto(packet, (serverName, serverPort))

# Receive Phase A Server Packet
response, serverAddress = clientSocket.recvfrom(2048)
data_length, pcode, Server_entity, repeat, udp_port, len, codeA = struct.unpack(f'!IHHIIHH', response)
print(f'The packet received from {serverAddress} is: {(data_length, pcode, Server_entity, repeat, udp_port, len, codeA)}')

# Change pcode to next phase
pcode = codeA

print('-'*15, 'Phase B', '-'*15)
packet_id = 0

while packet_id < repeat:
  try:
    # Send Phase B Client Packet
    data = bytearray(len + len%4)
    data_length = len + len%4
    format = f'!IHHI{data_length}s'
    packet = struct.pack(format, data_length, pcode, ENTITY, packet_id, data)
    print("PHASE B PCode:" ,pcode)
    clientSocket.sendto(packet, (serverName, udp_port))
    
    # wait for response
    # Receive Phase B-1 Server Packet
    acknowledgment, serverAddress = clientSocket.recvfrom(2048)
    data_length, pcode, entity, _packet_id = struct.unpack(f'!IHHI', acknowledgment)
    print(f'The packet received from {serverAddress} is: {(data_length, pcode, entity, _packet_id)}')
    
    packet_id += 1
  except Exception as e:
    print('\033[93m','='*5,'Timeout: trying again.','='*5,'\033[0m')
    print(e)

# Receive Phase B-2 Server Packet
packet, serverAddress = clientSocket.recvfrom(2048)
data_length, pcode, entity, tcp_port, codeB = struct.unpack('!IHHIH', packet)

# Change pcode to next phase
pcode = codeB

clientSocket.close()

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
packet = clientSocket.recv(1024)
data_length, pcode, server_entity, repeat2, len2, codeC, char = struct.unpack('!IHHIHHc', packet)
print(f'The packet received from {serverAddress} is: {(data_length, pcode, server_entity, repeat2, len2, codeC, char)}')

# Change pcode to next phase
pcode = codeC

# phase D
print('-'*15, 'Phase D', '-'*15)
data_length = 12
pcode = codeC
data = char
padding = bytearray(3)

for i in range(repeat2):
  # Send Phase D Client Packet
  packet = struct.pack(f'!IHH4s', data_length, pcode, ENTITY, data + padding)
  clientSocket.send(packet)
  sleep(0.5)

# Receive Phase D Server Packet
serverPacket = clientSocket.recv(2048)
data_length, pcode, entity, codeD = struct.unpack(f'!IHHH', serverPacket)
print(f'The packet received from {serverAddress} is: {(data_length, pcode, entity, codeD)}')

# Change pcode to next phase
pcode = codeD

clientSocket.close()

sys.exit() # Terminate the program after sending the corresponding data