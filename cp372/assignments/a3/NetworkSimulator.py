#from Random import *
import random 
from common import *
from Node import *
import copy

class NetworkSimulator:
    def __init__(self):
        print("Initializing Network Simulator")

        seed = 10 #int(input("Enter random seed: "))
        trace = 2 #int(input("Enter TRACE: "))        
        self.TRACE = trace
        
        num_nodes = 9 #int(input("Enter number of nodes:"))
        
        self.rand = random.seed(seed);        # instantiate Random number generator with provided seed

        sum = float(0.0);              # test random number generator for students 
        for i in range(0,1000):
            sum=sum+random.random()    # random.random() should be uniform in [0,1] 
        avg = sum/float(1000.0)
        if avg < 0.25 or avg > 0.75:
            print("It is likely that random number generation on your machine" )
            print("is different from what this emulator expects.  Sorry ")
            sys.exit()
        else:
            print("average = " + str(avg) )

        self.clocktime = 0.0;                    # initialise simulation time to 0
        self.NUM_NODES = num_nodes
        self.connectcosts = [ [0]*self.NUM_NODES for i in range(self.NUM_NODES) ]
        self.INFINITY = 999
        self.LINKCHANGES=1
        self.eventList =  EventList()          # instantiate event list (initially empty)

        if self.NUM_NODES == 4:
            self.connectcosts[0][0] = 0
            self.connectcosts[0][1] = 1
            self.connectcosts[0][2] = 3
            self.connectcosts[0][3] = 7
            self.connectcosts[1][0] = 1
            self.connectcosts[1][1] = 0
            self.connectcosts[1][2] = 1
            self.connectcosts[1][3] = self.INFINITY
            self.connectcosts[2][0] = 3
            self.connectcosts[2][1] = 1
            self.connectcosts[2][2] = 0
            self.connectcosts[2][3] = 2
            self.connectcosts[3][0] = 7
            self.connectcosts[3][1] = self.INFINITY
            self.connectcosts[3][2] = 2
            self.connectcosts[3][3] = 0
        elif self.NUM_NODES == 3:
            self.connectcosts[0][0] = 0
            self.connectcosts[0][1] = 2
            self.connectcosts[0][2] = 7            
            self.connectcosts[1][0] = 2
            self.connectcosts[1][1] = 0
            self.connectcosts[1][2] = 1            
            self.connectcosts[2][0] = 7
            self.connectcosts[2][1] = 1
            self.connectcosts[2][2] = 0
        elif self.NUM_NODES == 9:
            self.connectcosts[0][0] = 0
            self.connectcosts[0][1] = 8
            self.connectcosts[0][2] = self.INFINITY
            self.connectcosts[0][3] = 1
            self.connectcosts[0][4] = self.INFINITY
            self.connectcosts[0][5] = self.INFINITY
            self.connectcosts[0][6] = self.INFINITY
            self.connectcosts[0][7] = self.INFINITY
            self.connectcosts[0][8] = self.INFINITY
            
            self.connectcosts[1][0] = 8           
            self.connectcosts[1][1] = 0
            self.connectcosts[1][2] = 1
            self.connectcosts[1][3] = self.INFINITY            
            self.connectcosts[1][4] = 1
            self.connectcosts[1][5] = self.INFINITY
            self.connectcosts[1][6] = self.INFINITY
            self.connectcosts[1][7] = self.INFINITY
            self.connectcosts[1][8] = self.INFINITY

            self.connectcosts[2][0] = self.INFINITY           
            self.connectcosts[2][1] = 1
            self.connectcosts[2][2] = 0
            self.connectcosts[2][3] = self.INFINITY            
            self.connectcosts[2][4] = self.INFINITY
            self.connectcosts[2][5] = self.INFINITY
            self.connectcosts[2][6] = self.INFINITY
            self.connectcosts[2][7] = self.INFINITY
            self.connectcosts[2][8] = self.INFINITY
            
            self.connectcosts[3][0] = 1           
            self.connectcosts[3][1] = self.INFINITY
            self.connectcosts[3][2] = self.INFINITY
            self.connectcosts[3][3] = 0            
            self.connectcosts[3][4] = 1
            self.connectcosts[3][5] = self.INFINITY
            self.connectcosts[3][6] = 1
            self.connectcosts[3][7] = self.INFINITY
            self.connectcosts[3][8] = self.INFINITY

            self.connectcosts[4][0] = self.INFINITY           
            self.connectcosts[4][1] = 1
            self.connectcosts[4][2] = self.INFINITY
            self.connectcosts[4][3] = 1            
            self.connectcosts[4][4] = 0
            self.connectcosts[4][5] = 1
            self.connectcosts[4][6] = self.INFINITY
            self.connectcosts[4][7] = 1
            self.connectcosts[4][8] = self.INFINITY

            self.connectcosts[5][0] = self.INFINITY           
            self.connectcosts[5][1] = self.INFINITY
            self.connectcosts[5][2] = self.INFINITY
            self.connectcosts[5][3] = self.INFINITY
            self.connectcosts[5][4] = 1
            self.connectcosts[5][5] = 0
            self.connectcosts[5][6] = self.INFINITY
            self.connectcosts[5][7] = self.INFINITY
            self.connectcosts[5][8] = 1
            
            self.connectcosts[6][0] = self.INFINITY           
            self.connectcosts[6][1] = self.INFINITY
            self.connectcosts[6][2] = self.INFINITY
            self.connectcosts[6][3] = 1            
            self.connectcosts[6][4] = self.INFINITY
            self.connectcosts[6][5] = self.INFINITY
            self.connectcosts[6][6] = 0
            self.connectcosts[6][7] = 1
            self.connectcosts[6][8] = self.INFINITY

            self.connectcosts[7][0] = self.INFINITY           
            self.connectcosts[7][1] = self.INFINITY
            self.connectcosts[7][2] = self.INFINITY
            self.connectcosts[7][3] = self.INFINITY            
            self.connectcosts[7][4] = 1
            self.connectcosts[7][5] = self.INFINITY
            self.connectcosts[7][6] = 1
            self.connectcosts[7][7] = 0
            self.connectcosts[7][8] = 1
            
            self.connectcosts[8][0] = self.INFINITY           
            self.connectcosts[8][1] = self.INFINITY
            self.connectcosts[8][2] = self.INFINITY
            self.connectcosts[8][3] = self.INFINITY            
            self.connectcosts[8][4] = self.INFINITY
            self.connectcosts[8][5] = 1
            self.connectcosts[8][6] = self.INFINITY
            self.connectcosts[8][7] = 1
            self.connectcosts[8][8] = 0
            
                
        
        self.nodes = [None]*self.NUM_NODES   
        for i in range(self.NUM_NODES):
            self.nodes[i] = Node(i, self, self.connectcosts[i])

        if self.TRACE > 1:
            print("\nInitial Distance Tables:")    
            for i in range(self.NUM_NODES):
                self.nodes[i].printdt()


        #  initialize future link changes
        if self.LINKCHANGES:            
            ev = Event()
            ev.event_time = 100000.0
            ev.event_type = EventType.LINK_CHANGE
            ev.entity = 0
            ev.rtpacket = None
            ev.dest = 3
            ev.cost = 1            
            self.eventList.add(ev)
            

            ev = Event()
            ev.event_time = 200000.0
            ev.event_type = EventType.LINK_CHANGE
            ev.entity = 0
            ev.rtpktptr = None
            ev.dest = 1
            ev.cost = 6            
            self.eventList.add(ev)
                                      
    def runSimulator(self):        

        #Begin the main simulation loop
        #self.eventList.print_evlist()
        while True: 
            # Get the next event in the list
            next_event = self.eventList.removeNext();

            if next_event == None:
                # we run out of events - exit - end of simulation
                break
                
            if self.TRACE > 1:
                print("\nMAIN: receive event, time="+str(next_event.event_time)+ "at "+str(next_event.entity))
                

                if (next_event.event_type == EventType.FROM_LAYER2):
                    #print("Node " + str(next_event.rtpacket.destid) + " Received a packet from Node "+ str(next_event.rtpacket.sourceid) +" at time " + str(next_event.event_time))
                    print("Node " + str(next_event.rtpacket.destid) + " Received a packet from Node "+ str(next_event.rtpacket.sourceid) )
                    print("Event Type:  FROM_LAYER2 ", end=" ")
                    print(" src:",next_event.rtpacket.sourceid, end=" ")
                    print(" dest:",next_event.rtpacket.destid, end=" ")
                    print(" contents: ", end="")
                    for i in range(self.NUM_NODES): 
                        print(next_event.rtpacket.mincosts[i], "  ", end="")
                    print()
                                                                     
                                
            #Advance the simulator's time to be the scheduled time of the next event
            self.clocktime = next_event.event_time

            #Perform the appropriate action based on the event 
            if next_event.event_type == EventType.FROM_LAYER2:
                if next_event.entity >= 0 and next_event.entity < self.NUM_NODES:
                    
                    changed = self.nodes[next_event.entity].recvUpdate(next_event.rtpacket)
                    #if changed:
                    #    print("Node "+str(next_event.entity)+" has updates and is sending the updates to the neighbors, here is the updated distance table")
                    #    self.nodes[next_event.entity].printdt()
                    #else:
                    #    print("Distance table for Node "+str(next_event.entity)+" has not changed")
                            
                else:
                    print("Panic: unknown event entity\n")
                    sys.exit('Panic')

            #elif next_event.event_type == EventType.LINK_CHANGE:               
            else:
                print("INTERNAL: 147 PANIC: Unknown event type")
                                       
        print("\nSimulator terminated at t="+str(self.clocktime)+" no packets in medium\n")
        
        if self.TRACE >= 1:  
            for i in range(self.NUM_NODES):
                print("distance Table for node", i, "\n")            
                self.nodes[i].printdt()
                        
                print("Path to reach other nodes:", self.nodes[i].routes)
                print('\n\n')               

    def tolayer2(self, packet):

        #be nice: check if source and destination id's are reasonable
        if packet.sourceid < 0 or packet.sourceid > self.NUM_NODES - 1:
            print ("WARNING: illegal source id in your packet, ignoring packet!\n")
            return
       
        if packet.destid < 0 or packet.destid > self.NUM_NODES - 1:
            print("WARNING: illegal dest id in your packet, ignoring packet!\n")
            return
   
        if packet.sourceid == packet.destid:
            print("WARNING: source and destination id's the same, ignoring packet!\n")
            return
        
        if self.connectcosts[packet.sourceid][packet.destid] == self.INFINITY:
            print("WARNING: source and destination not connected, ignoring packet!\n")
            return

        mypacket = copy.deepcopy(packet)
        mypacket.sourceid = packet.sourceid
        mypacket.destid = packet.destid
        for i in range(self.NUM_NODES):
            mypacket.mincosts[i] = packet.mincosts[i]
 
        
        if self.TRACE > 1 :
            print("\nTOLAYER2: source: " +  str(mypacket.sourceid) + " dest: " + str(mypacket.destid) + ": costs: ", end="") 
            for i in range(self.NUM_NODES):
                print(str(mypacket.mincosts[i]) + " ", end="" )
            print()
            if self.nodes[mypacket.sourceid] is not None:
                self.nodes[mypacket.sourceid].printdt()
            
            #print(self.nodes[mypacket.sourceid])
            
        # create future event for arrival of packet at the other side
        ev = Event()
        ev.event_type = EventType.FROM_LAYER2 # packet will pop out from layer3
        ev.entity = packet.destid  # event occurs at other entity
        ev.rtpacket = mypacket       # save ptr to my copy of packet

        # compute the arrival time of packet at the other end.
        # medium can not reorder, so make sure packet arrives between 1
        # and 10 time units after the latest arrival time of packets
        # currently in the medium on their way to the destination
        lastime = self.clocktime;        
        lastime_in_list = self.eventList.getLastTime()
        
        if lastime_in_list != None:
            lastime = lastime_in_list
                        
        arrivalTime  = lastime + 9.0 * random.random() + 1.0
        ev.event_time = arrivalTime

        if self.TRACE > 2:
            print("TOLAYER2: scheduling arrival on other side" + str(arrivalTime))


        self.eventList.add(ev)      

if __name__ == '__main__':
    simulator = NetworkSimulator()

    simulator.runSimulator()

