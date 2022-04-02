from common import *

class Node:
    def __init__(self, ID, networksimulator, costs):
        self.myID = ID
        self.ns = networksimulator
        num = self.ns.NUM_NODES        
        self.distanceTable = [[0 for i in range(num)] for j in range(num)]
        self.routes = [0 for i in range(num)]
        self.neighbours = []

        for i in range(num):
            for j in range(num):
                if i == ID:
                    self.distanceTable[i][j] = costs[j]         # fill array with costs
                elif i == j:
                    self.distanceTable[i][j] = 0                # set diagonal to 0
                else:
                    self.distanceTable[i][j] = self.ns.INFINITY # no link, set inf
                    
        
                
        for did in range(num):
            if self.distanceTable[ID][did] != self.ns.INFINITY:
                self.routes[did] = did
                
                if did != ID:
                    self.neighbours.append(did)
            else:
                self.routes[did] = -1 # if we have no route at first set it to -1
                
        self.updateNeighbors()

    def recvUpdate(self, pkt):
        """
        When a router receives an update from its neighbors, it
        uses that to update its distance table. The value a node receives is in pkt which
        is an object of the class of RTPacket defined in common.py. If there is any
        change in the distance table of the node, then it informs its directly connected
        neighbors. The communication is done by sending a packet of type RTPacket.
        """
        # Updates local version of source row
        self.distanceTable[pkt.sourceid] = pkt.mincosts
        # you implement the rest of it  

        changed = False
                    
        for node in range(self.ns.NUM_NODES): # loop over all nodes
            if node != self.myID: # if it is not the node itself
                shortest_path = self.distanceTable[self.myID][node] # current shortest path to node

                for neighbour in self.neighbours: # loop over all neighbours
                    if self.distanceTable[self.myID][neighbour] + self.distanceTable[neighbour][node] < shortest_path: # if the distance to node i is shorter than the current shortest path
                        shortest_path = self.distanceTable[self.myID][neighbour] + self.distanceTable[neighbour][node] # update shortest path
                        self.distanceTable[self.myID][node] = shortest_path # update distance table
                        self.routes[node] = deepcopy(neighbour) # update routes
                        changed = True # update changed
        
        
        if changed:
            self.updateNeighbors()
              
        return 
    
    
    def updateNeighbors(self):
        """
        Sends a nodes distance table to all its neighbors.
        """

        for i in self.neighbours:
            if self.distanceTable[self.myID][i] != self.ns.INFINITY and i != self.myID:
                packet = RTPacket(self.myID, i, self.distanceTable[self.myID])
                self.ns.tolayer2(packet)

    
    def printdt(self):
        print("   D"+str(self.myID)+" |  ", end="")
        for i in range(self.ns.NUM_NODES):
            print("{:3d}   ".format(i), end="")
        print()
        print("  ----|-", end="")
        for i in range(self.ns.NUM_NODES):            
            print("------", end="")
        print()    
        for i in range(self.ns.NUM_NODES):
            print("     {}|  ".format(i), end="" )
            
            for j in range(self.ns.NUM_NODES):
                print("{:3d}   ".format(self.distanceTable[i][j]), end="" )
            print()            
        print()
        
