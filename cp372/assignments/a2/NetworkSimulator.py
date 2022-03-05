#from Random import *
import random 
from common import *
from sender import *
from receiver import *
import copy
import sys

class NetworkSimulator:
    def __init__(self):
        print("Initializing Network Simulator")

    
    def initSimulator(self, maxMsgs, loss, corrupt, delay, seed, trace):
        self.maxMessages = maxMsgs 
    
        self.lossProb = loss 
        self.corruptProb = corrupt 
    
        self.avgMessageDelay = delay 
    
        self.rand = random.seed(seed)         # instantiate Random number generator with provided seed
    
        self.nMsgSim = 0                        # initialise number of simulated messages to 0
        self.time = 0.0                         # initialise simulation time to 0

        self.trace = trace
    
        self.eventList =  EventList()          # instantiate event list (initially empty)
    
        self.sender = sender(A, self)           # initialise sender entity (the respective class)
    
        self.receiver = receiver(B, self)       # initialise receiver entity (the respective class)



    def runSimulator(self):
        self.sender.init()
        self.receiver.init()

        sum = float(0.0)               # test random number generator for students 
        for i in range(0,1000):
            sum=sum+random.random()    # random.random() should be uniform in [0,1] 
        avg = sum/float(1000.0)
        if avg < 0.25 or avg > 0.75:
            print("It is likely that random number generation on your machine" )
            print("is different from what this emulator expects.  Sorry ")
            sys.exit()
        else:
            print("average = " + str(avg) )

        self.generateNextArrival()

        #Begin the main simulation loop
        while True: 
            # Get the next (with respect to the scheduled time) event in the list
            next_event = self.eventList.removeNext() 

            if next_event == None:
                # we run out of events - exit - end of simulation
                break
                
            if self.trace >= 1:
                print("---------------------------------------")
            if self.trace >= 2:    
                print("")
                #print("EVENT #: "+ str(self.nMsgSim))
                print("EVENT time: " + str(next_event.time)) 

                if (next_event.event_type == EventType.TIMERINTERRUPT):
                    print("Event type:  TIMERINTERRUPT ")
                elif (next_event.event_type == EventType.FROMAPP):
                    print("Event type:  FROMAPP")
                elif (next_event.event_type == EventType.FROMNETWORK):
                    print("Event type:  FROMNETWORK")
                else:
                    print("Event type: " + str(next_event.event_type))


                print("Event entity: " + str(next_event.entity)) 
            
            #Advance the simulator's time to be the scheduled time of the next event
            self.time = next_event.time


            #Perform the appropriate action based on the event 
            if next_event.event_type == EventType.TIMERINTERRUPT:
                if next_event.entity == A:
                    if self.trace >=1 :
                        print("A: sending the last packet again")
                    self.sender.timerInterrupt()
                else:
                    print("INTERNAL PANIC: Timeout for invalid entity")
                    
            elif next_event.event_type == EventType.FROMNETWORK:
                if next_event.entity == A:
                    if self.trace >= 1:
                        print("A: receiving an acknowledgement packet")
                    self.sender.input(next_event.packet)
                elif next_event.entity == B:
                    if self.trace >= 1:
                        print("B: Receiving the data and sending the acknowledgement " + next_event.packet.payload )
                    self.receiver.input(next_event.packet)
                else:
                    print("INTERNAL PANIC: Packet has arrived for unknown entity") 
            elif next_event.event_type == EventType.FROMAPP:

                    nextMessage = ''

                    # Now, let's generate the contents of this message
                    j = chr(((self.nMsgSim - 1) % 26) + 97)
                    for i in range(0, MAXDATASIZE):
                        nextMessage += j
                    
                    #Let the sender handle the new message
                    if self.trace >= 1:
                        print("A: Sending the data " + nextMessage)
                    self.sender.output(Message(nextMessage))
                    
                    # If a message has arrived from sending process, we need to schedule the arrival of the next message
                    # If we've reached the maximum message count, exit the main loop
                    if (self.nMsgSim < self.maxMessages):
                        self.generateNextArrival()
                    else:
                        if self.trace >= 1:
                            print("do not schedule: the maximum number of messages is scheduled")    
                        #do not schedule more FROMAPP events from the application layer if we reached the maximum number of messages
            else:
                print("INTERNAL PANIC: Unknown event type")


    def generateNextArrival(self):
        #print("generateNextArrival(): called")

        # arrival time 'x' is uniform on [0, 2 * avgMessageDelay] having mean of avgMessageDelay.
        x = self.avgMessageDelay * random.random() * 2

        #Instantiate a new FROMAPP Event
        next_event = Event(self.time + x, EventType.FROMAPP, A)
    
        #Add the newly instantiated Event to the EventList
        self.eventList.add(next_event) 
    
        # Increment the message counter
        self.nMsgSim += 1
        
        if self.trace >= 2:
            print("generateNextArrival(): time is " + str(self.time)) 
            print("generateNextArrival(): future time for " + "event " + str(next_event.event_type) + " at entity " + str(next_event.entity) + " will be " + str(next_event.time)) 
    

    def startTimer(self, entity, increment):
        if self.trace >= 1:
            print("startTimer: starting timer at " + str(self.time))

        t = self.eventList.removeTimer(entity) 
        #t = self.eventList.removeTimer(AorB) 

        if (t != None):
            print("startTimer: Warning: Attempting to start a timer that is already running")
            self.eventList.add(t)
        else:
            timer_event = Event(self.time + increment, EventType.TIMERINTERRUPT, entity) 
            self.eventList.add(timer_event) 


    def stopTimer(self, entity):
        
        if self.trace >= 1:
            print("stopTimer: stopping timer at " + str(self.time))
        timer_event = self.eventList.removeTimer(entity)
        if (timer_event == None):
            if self.trace >= 1:
                print("stopTimer: Warning: Unable to cancel your timer, which is not set.")
                                                    
    def udtSend(self, entity, p):

        #int destination 
        #double arrivalTime 

        # Use a copy of the supplied packet at this method may corrupt its data.
        # We want to keep the original copy for retransmission purposes
       
        packet = copy.deepcopy(p)
        
        if packet==None:
            if self.trace >= 1:
                print("udtSend: None" )
        else:    
            if self.trace >= 1:
                print("udtSend: " + packet.toStr())


        #Set destination to be the 'other side' of the network  B if we are A or vice versa.
        if entity == A:
            destination = B
        elif entity == B:
            destination = A 
        else:
            if self.trace >= 1:
                print("entity = " + str(entity))
                print("udtSend: Warning: invalid packet sender")
            return 

        #Simulate losses by doing nothing
        if (random.random() < self.lossProb): 
            if self.trace >= 1:
                print("udtSend: SIMULATING PACKET LOSS")
            return 
    
        #Simulate corruption
        if (random.random() < self.corruptProb):
            if self.trace >= 1:
                print("udtSend: SIMULATING PACKET BEING CORRUPTED")

            x = random.random()
            if (x < 0.75):
                #corrupt the payload - by changing the first character
                payload = packet.payload 
                if len(payload) < 2:
                    payload = "="
                else:
                    #print("--------------------------"+payload)
                    #payload = "=" + payload.substring(1) 
                    payload = "=" + payload[1:]
                    #print("--------------------------"+payload)

                packet.payload = payload

            elif (x < 0.875):
                #corrupt the sequence number
                packet.seqNum = random.randint(10, 20)
            else:
                #corrupt the acknowledgment number
                packet.ackNum = random.randint(10, 20)


        #Decide when the packet will arrive.  
        #Since the medium cannot reorder, the packet will arrive 1 to 10 time units after the last packet sent by this sender
        arrivalTime = self.eventList.getLastPacketTime(destination)

        if (arrivalTime <= 0.0):
            arrivalTime = self.time

        arrivalTime = arrivalTime + 1.0 + 9.0 * random.random() 

        #Finally, create and schedule this event
        if self.trace >=2 :
            print("udtSend: Scheduling arrival on other side: at time   " + str(arrivalTime))

        arrival = Event(arrivalTime, EventType.FROMNETWORK, destination, packet) 
        self.eventList.add(arrival)
        

    def deliverData(self, entity, dataSent):
        if self.trace >= 1:
            print("B: deliverData: data received at " + str(entity) + ":")
        #print(dataSent)


