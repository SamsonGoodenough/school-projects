"""
  Assignment 2: sender.py
    3/4/2022 | cp372
  
  Group Members:
    Riley Huston (190954880)
    hust4880@mylaurier.ca
    Samson Goodenough (190723380)
    good3380@mylaurier.ca
"""

from common import *

class sender:
  RTT = 20
  
  def isCorrupted (self, packet):
    '''Checks if a received packet (acknowledgement) has been corrupted
    during transmission.
    Return true if computed checksum is different than packet checksum.
    '''
    checksum = checksumCalc(packet.payload) + packet.ackNum + packet.seqNum 
    # if(checksum != packet.checksum):
    #   print('\033[91m','='*5,'ERROR:',"Packet has been Corrupted: ",checksum, "!=", packet.checksum,'='*5,'\033[0m')
    return checksum != packet.checksum

  def isDuplicate(self, packet):
    '''checks if an acknowledgement packet is duplicate or not
    similar to the corresponding function in receiver side
    '''
    # if(self.packet.seqNum != packet.ackNum):
    #   print('\033[91m','='*5,'ERROR:', "Duplicate Packet Recieved", self.packet.seqNum, "!=", packet.ackNum,'='*5,'\033[0m')
    return self.packet.seqNum != packet.ackNum
 
  def getNextSeqNum(self):
    '''generate the next sequence number to be used.
    '''
    return 1 - self.seqNum

  def __init__(self, entityName, ns):
    self.entity = entityName
    self.networkSimulator = ns
    print("Initializing sender: A: "+str(self.entity))

  def init(self):
    '''initialize the sequence number and the packet in transit.
    Initially there is no packet is transit and it should be set to None
    '''
    self.packet = None
    self.inTransit = False
    self.seqNum = 0
    return

  def timerInterrupt(self):
    '''This function implements what the sender does in case of timer
    interrupt event.
    This function sends the packet again, restarts the time, and sets
    the timeout to be twice the RTT.
    You never call this function. It is called by the simulator.
    '''
    #printError("Sender Timeout!!! Trying again...")
    self.networkSimulator.udtSend(self.entity, self.packet)
    self.networkSimulator.startTimer(self.entity, self.RTT*2)
    return


  def output(self, message):
    '''prepare a packet and send the packet through the network layer
    by calling utdSend.
    It also start the timer.
    It must ignore the message if there is one packet in transit
    '''
    if(not self.inTransit):
      seqNum = self.seqNum
      payload = message.data
      chksum = checksumCalc(payload) + seqNum
      self.packet = Packet(seqNum, 0, chksum, payload)

      self.networkSimulator.udtSend(self.entity, self.packet)
      self.networkSimulator.startTimer(self.entity, self.RTT)
      self.inTransit = True
    return
 
  
  def input(self, packet):

    '''If the acknowlegement packet isn't corrupted or duplicate, 
    transmission is complete. Therefore, indicate there is no packet
    in transition.
    The timer should be stopped, and sequence number  should be updated.

    In the case of duplicate or corrupt acknowlegement packet, it does 
    not do anything and the packet will be sent again since the
    timer will be expired and timerInterrupt will be called by the simulator.
    '''
    if(not (self.isCorrupted(packet) or self.isDuplicate(packet))):
      self.inTransit = False
      self.networkSimulator.stopTimer(self.entity)
      self.seqNum = self.getNextSeqNum()
    return 
