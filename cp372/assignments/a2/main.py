from NetworkSimulator import *
from common import *
import sys

def getSimulatorParameter():
  print("Network Simulator")
  nMsgSim = int(input("Enter number of messages to simulate (> 0): ")) #5
  if nMsgSim <= 0 :
    print("Number of Messages must be > 0")
    sys.exit()
 
  loss = float(input("Enter the packet loss probability (0.0 for no " + "loss): "))
  #loss = 0.0 
  if ((loss < 0) or (loss > 1)):
    print("packet loss probability must be > 0.0 and < 1.0")
    sys.exit()

  corrupt = float(input("Enter the packet corruption probability (0.0 " + "for no corruption): "))
  #corrupt = 0.0 
  if ((corrupt < 0) or (corrupt > 1)):
    print("packet corruption probability must be > 0.0 and < 1.0")
    sys.exit()

  delay = float(input("Enter the average time between messages from the sender's application layer (> 0.0): ")) #1000
  if (delay < 0):
    print("Number of Messages must be > 0.0")
    sys.exit()

  seed = 10 #int(input("Enter random seed: "))  
  
  trace = 1 #int(input("Enter random seed: "))


  return nMsgSim, loss, corrupt, delay, seed, trace


if __name__ == '__main__':
  maxMsgs, loss, corrupt, delay, seed, trace = getSimulatorParameter()
  simulator = NetworkSimulator()
  simulator.initSimulator(maxMsgs, loss, corrupt, delay, seed, trace)
  simulator.runSimulator()



