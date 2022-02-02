"""
  Assignment 1: Server
    1/26/2022 | cp372
  
  Group Members:
    Riley Huston (190954880)
    hust4880@mylaurier.ca
    Samson Goodenough (190723380)
    good3380@mylaurier.ca
"""

import random
import struct
from socket import * 
import sys

"""
----------------------------------------------------------
Validates the header of a unpacked packet for a UDP server
Use: if valdiateHeaderUDP(UDPsocket, packet, clientAddress, server_pcode):
----------------------------------------------------------
Parameters:
  serverSocket  - the socket in case of error
  unpacket      - the unpacked packet
  server_pcode  - the server pcode
----------------------------------------------------------
"""
def valdiateHeaderUDP(UDPsocket, packet, clientAddress, server_pcode, debug = False):
  data_length, pcode, entity = struct.unpack('!IHH', packet[:8])
  if debug: print("UDP VALIDATION CLIENT: ",data_length, pcode, entity)
  valid = True
  if data_length%4 != 0:
    valid = False
    if debug: print("DATA LENGTH:", data_length)
  elif pcode != server_pcode:
    valid = False
    if debug: print("PCODE:", pcode ,server_pcode)
  elif entity != 1:
    valid = False
    if debug: print("ENTITY:", entity)
  
  if not valid:
    UDPsocket.sendto(b'-1', clientAddress)
    UDPsocket.close()
    sys.exit()

"""
----------------------------------------------------------
Validates the header of a unpacked packet for a TCP server
Use: if valdiateHeaderTCP(TCPsocket, packet, server_pcode):
----------------------------------------------------------
Parameters:
  serverSocket  - the socket in case of error
  unpacket      - the unpacked packet
  server_pcode  - the server pcode
----------------------------------------------------------
"""
def valdiateHeaderTCP(TCPsocket, packet, server_pcode, debug = False):
  data_length, pcode, entity = struct.unpack('!IHH', packet[:8])
  valid = True
  if debug: print("TCP VALIDATION CLIENT: ",data_length, pcode, entity)
  if data_length%4 != 0:
    valid = False
    if debug: print("DATA LENGTH:", data_length)
  elif pcode != server_pcode:
    valid = False
    if debug: print("PCODE:", pcode ,server_pcode)
  elif entity != 1:
    valid = False
    if debug: print("ENTITY:", entity)
  if not valid:
    TCPsocket.send(b'-1')
    TCPsocket.close()
    sys.exit()
    
def padding(length):
  return 0 if (4-length%4 == 4) else 4-length%4 

def printError(error):
  print('\033[91m','='*5,'ERROR:',error,'='*5,'\033[0m')

print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

# constants
HEADER_SIZE = 8
ENTITY = 2 # server

# assign a port number
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.settimeout(3)

# bind the socket to server address and server port
serverSocket.bind(("", serverPort))

# Receive Phase A Client Packet
packet, clientAddress = serverSocket.recvfrom(1024)
try:
  # Validate packet header
  pcode = 0 # phase 1
  valdiateHeaderUDP(serverSocket, packet, clientAddress, pcode)
  unpacked = struct.unpack(f'!IHH{len(packet) - HEADER_SIZE}s', packet)
 
except Exception as e:
  printError(f"Packet received from {clientAddress} is not valid, closing server.")
  printError(e)
  serverSocket.close()
  sys.exit()

print(f'Packet received from {clientAddress}\n\tpacket is: \t{unpacked[:-1]}\n\tdata is: \t{unpacked[-1]}')

if unpacked[-1].decode() == 'Hello World!!!':
  print('\tThe packet is correct')

# response
data_length = 16

repeat = random.randint(5, 20)
udp_port = random.randint(20000, 30000)
len1 = random.randint(50, 100)
codeA = random.randint(100, 400)

# Send Phase A Server Packet
response = struct.pack('!IHHIIHH', data_length, pcode, ENTITY, repeat, udp_port, len1, codeA)
serverSocket.sendto(response, clientAddress)

# Change pcode to next phase
pcode = codeA

# kill the socket
serverSocket.close()

# listen to new port
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(("", udp_port))
serverSocket.settimeout(3)
print('-'*15, 'End of Phase A', '-'*15)
print('-'*15, 'Phase B', '-'*15)
data_length = 4
i = 0

while i < repeat:
  # Receive Phase B Client Packet
  packet, clientAddress = serverSocket.recvfrom(1024)
  if random.random() > (1/5):
    try:
      format = f'!IHHI{len1+padding(len1) + 4}s'

      # Validate packet header
      valdiateHeaderUDP(serverSocket, packet, clientAddress, pcode)
      unpacked = struct.unpack(format, packet)
      unpacked_data = unpacked[-1].replace(b"\x00", b"0")
      packet_id = unpacked[3]

      if(len(unpacked_data) != len1 + padding(len1) + 4):
        raise Exception("Length of data is incorrect.")
      if(packet_id != i):
        raise Exception("Packet sent out of order.")

      print(f'Packet received from {clientAddress}\n\tpacket is: \t{unpacked[:-1]}\n\tdata is: \t0*{len1+padding(len1)}')
    except Exception as e:
      printError(f"Packet received from {clientAddress} is not valid, closing server.")
      printError(e)
      serverSocket.sendto(b'-1', clientAddress)
      serverSocket.close()
      sys.exit()

    # Send Phase B-1 Server Packet
    
    data_length = 4
    acknowledgment = struct.pack('!IHHI', data_length, pcode, ENTITY, packet_id)
    serverSocket.sendto(acknowledgment, clientAddress)

    i += 1
    
  else:
    print('\033[93m','='*5,'Warning: Packet Dropped!','='*5,'\033[0m')

# Send Phase B-2 Server Packet
data_length = 6
tcp_port = random.randint(20000, 30000)
codeB = random.randint(100,400)
serverPacket = struct.pack('!IHHII', data_length, pcode, ENTITY, tcp_port, codeB)
serverSocket.sendto(serverPacket, clientAddress)
serverSocket.close()

# Change pcode to next phase
pcode = codeB
print('-'*15, 'End of Phase B', '-'*15)
# phase C
print('-'*15, 'Phase C', '-'*15)

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(("", tcp_port))
serverSocket.settimeout(6)
serverSocket.listen(5)
connectionSocket, addr = serverSocket.accept()
print ('The server is ready to receive')

data_length = 15 # IHHIHc
pcode = codeB
repeat2 = random.randint(5, 20)
len2 = random.randint(50, 100)
codeC = random.randint(100, 400)
char = chr(random.randint(65, 90))
# Send Phase C Server Packet
serverPacket = struct.pack('!IHHIIIc', data_length, pcode, ENTITY, repeat2, len2, codeC, char.encode())
connectionSocket.send(serverPacket)

# Change pcode to next phase
pcode = codeC

print('-'*15, 'End of Phase C', '-'*15)
# phase D
print('-'*15, 'Phase D', '-'*15)

# Receive Phase D Client Packet
for i in range(repeat2):
  packet = connectionSocket.recv(2048)
  # Validate packet header
  try:
    valdiateHeaderTCP(serverSocket, packet, pcode)
    unpacked = struct.unpack(f'!IHH{len2 + padding(len2)}s', packet)
  except Exception as e:
    printError(f"Packet received from {clientAddress} is not valid, closing server.")
    printError(e)
    serverSocket.close()
    sys.exit()
  
  print(f'Packet received from {clientAddress}\n\tpacket is: \t{unpacked[:-1]}\n\tdata is: \t{unpacked[-1].decode()[0]}*{unpacked[0]}')
  
data_length = 15 # IHHIHc
pcode = codeC
codeD = random.randint(100, 400)
serverPacket = struct.pack('!IHHI', data_length, pcode, ENTITY, codeD)
connectionSocket.send(serverPacket)
print('-'*15, 'End of Phase D', '-'*15)
serverSocket.close()
sys.exit() # Terminate the program after sending the corresponding data

