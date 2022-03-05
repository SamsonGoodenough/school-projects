"""
  Assignment 2: receiver.py
    3/4/2022 | cp372
  
  Group Members:
    Riley Huston (190954880)
    hust4880@mylaurier.ca
    Samson Goodenough (190723380)
    good3380@mylaurier.ca
"""

from common import *

class receiver:
  
  def isCorrupted(self, packet):
    ''' Checks if a received packet has been corrupted during transmission.
    Return true if computed checksum is different than packet checksum.'''

    checksum = checksumCalc(packet.payload) + packet.ackNum + packet.seqNum 
    # if(checksum != packet.checksum):
    #   print('\033[91m','='*5,'ERROR:',"Packet has been Corrupted: ",checksum, "!=", packet.checksum,'='*5,'\033[0m')
    return checksum != packet.checksum
   
  def isDuplicate(self, packet):
    '''checks if packet sequence number is the same as expected sequence number'''
    
    # if(self.expectedSeqNum != packet.seqNum):
    #   print('\033[91m','='*5,'ERROR:', "Duplicate Packet Recieved", self.expectedSeqNum, "!=", packet.seqNum,'='*5,'\033[0m')
    return self.expectedSeqNum != packet.seqNum  

  def getNextExpectedSeqNum(self):
    '''The expected sequence numbers are 0 or 1'''
    return 1 - self.expectedSeqNum
  
  
  def __init__(self, entityName, ns):
    self.entity = entityName
    self.networkSimulator = ns
    print("Initializing receiver: B: "+str(self.entity))


  def init(self):
    '''initialize expected sequence number'''
    self.expectedSeqNum = 0
    return
     

  def input(self, packet):
    '''This method will be called whenever a packet sent 
    from the sender arrives at the receiver. If the received
    packet is corrupted or duplicate, it sends a packet where
    the ack number is the sequence number of the  last correctly
    received packet. Since there is only 0 and 1 sequence numbers,
    you can use the sequence number that is not expected.
    
    If packet is OK (not a duplicate or corrupted), deliver it to the
    application layer and send an acknowledgement to the sender
    '''
    if(not (self.isCorrupted(packet) or self.isDuplicate(packet))):
      self.networkSimulator.deliverData(self.entity, packet.payload)
      ackPacket = Packet(0, self.expectedSeqNum, self.expectedSeqNum, '')
      self.networkSimulator.udtSend(self.entity, ackPacket)
      self.expectedSeqNum = self.getNextExpectedSeqNum()

    else:
      ackPacket = Packet(0, 1 - self.expectedSeqNum, 1 - self.expectedSeqNum, '')
      self.networkSimulator.udtSend(self.entity, ackPacket)

    return
