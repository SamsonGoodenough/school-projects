# Import socket module
from ctypes import sizeof
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
def valdiateHeaderUDP(UDPsocket, packet, clientAddress, server_pcode):
  data_length, pcode, entity = struct.unpack('!IHH', packet[:3])
  valid = True
  if data_length%4 != 0:
    valid = False
  if pcode == server_pcode:
    valid = False
  if entity == 2:
    valid = False
  if not valid:
    UDPsocket.sendto(-1, clientAddress)
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
def valdiateHeaderTCP(TCPsocket, packet, server_pcode):
  data_length, pcode, entity = struct.unpack('!IHH', packet[:3])
  valid = True
  if data_length%4 != 0:
    valid = False
  if pcode == server_pcode:
    valid = False
  if entity == 2:
    valid = False
  if not valid:
    TCPsocket.send(-1)
    TCPsocket.close()
    sys.exit()


print('\n\n\n\n\n\n\n')
print('-'*15, 'Phase A', '-'*15)

# constants
HEADER_SIZE = 8
ENTITY = 2 # server

# assign a port number
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_DGRAM)

# bind the socket to server address and server port
serverSocket.bind(("", serverPort))

print('The server is ready to receive')

# Receive Phase A Client Packet
packet, clientAddress = serverSocket.recvfrom(1024)
unpacked = struct.unpack(f'!IHH{len(packet) - HEADER_SIZE}s', packet)
pcode = 0 # phase 1

# Validate packet header
valdiateHeaderUDP(serverSocket, packet, clientAddress, pcode)

print(f'The packet received from {clientAddress} is: {unpacked}')
print(f'\tThe data is: {unpacked[-1].decode()}')
if unpacked[-1].decode() == 'Hello World!!!':
  print('\tThe packet is correct')

# response
data_length = 16

repeat = random.randint(5, 20)
udp_port = random.randint(20000, 30000)
len = random.randint(50, 100)
codeA = random.randint(100, 400)

# Send Phase A Server Packet
response = struct.pack('!IHHIIHH', data_length, pcode, ENTITY, repeat, udp_port, len, codeA)
serverSocket.sendto(response, clientAddress)

# Change pcode to next phase
pcode = codeA

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

    # Receive Phase B Client Packet
    unpacked = struct.unpack(format, packet)
    unpacked_data = unpacked[-1].replace(b"\x00", b"0")
    print(f'Packet received from {clientAddress}\n\tpacket is: \t{unpacked[:-1]}\n\tdata is: \t0*{len+len%4}')
    
    # Validate packet header
    valdiateHeaderUDP(serverSocket, packet, clientAddress, pcode)
    
    # Send Phase B-1 Server Packet
    packet_id = unpacked[3]
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
serverPacket = struct.pack('!IHHIH', data_length, pcode, ENTITY, tcp_port, codeB)
serverSocket.sendto(serverPacket, clientAddress)
serverSocket.close()

# Change pcode to next phase
pcode = codeB

# phase C
print('-'*15, 'Phase C', '-'*15)

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(("", tcp_port))
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
serverPacket = struct.pack('!IHHIHHc', data_length, pcode, ENTITY, repeat2, len2, codeC, char.encode())
connectionSocket.send(serverPacket)

# Change pcode to next phase
pcode = codeC

# phase D
print('-'*15, 'Phase D', '-'*15)

# Receive Phase D Client Packet

for i in range(repeat2):
  packet = connectionSocket.recv(2048)
  unpacked = struct.unpack('!IHH4s', packet)

  # Validate packet header
  valdiateHeaderTCP(serverSocket, packet, pcode)

  print(unpacked)


data_length = 15 # IHHIHc
pcode = codeC
codeD = random.randint(100, 400)
serverPacket = struct.pack('!IHHH', data_length, pcode, ENTITY, codeD)
connectionSocket.send(serverPacket)
sys.exit() # Terminate the program after sending the corresponding data

