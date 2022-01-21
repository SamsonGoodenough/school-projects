# Import socket module
from socket import * 
import sys # In order to terminate the program

serverName = 'localhost'
#serverName = '10.84.88.53'
# Assign a port number
serverPort = 12000

# Bind the socket to server address and server port
clientSocket = socket(AF_INET, SOCK_DGRAM)
sentence = input(' Input lower case sentence: ')

#clientSocket.connect((serverName, serverPort))
clientSocket. sendto( sentence.encode(), (serverName, serverPort))
modifiedSentence, serverAddress = clientSocket.recvfrom(2048)

print('From server: ', modifiedSentence.decode())
clientSocket.close()