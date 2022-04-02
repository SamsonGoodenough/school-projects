TRACE = 1
from copy import deepcopy
class RTPacket:
    def __init__(self, sid, did, mincosts):
        self.sourceid = sid
        self.destid = did
        self.mincosts = deepcopy(mincosts)

    def toStr(self):
        return "sourceid: "+str(self.sourceid) + " destid: "+ str(self.destid) 


class Event:
    def __init__(self, t=0, ty=0, ent=0, p=None):
        #self.self = self
        self.event_time = t
        self.event_type = ty
        self.entity = ent
        self.rtpacket = p 
        self.dest = None
        self.cost = None
    def __str__(self):
        return "time: " + str(self.event_time)    
    
class EventType:
    LINK_CHANGE = 11
    FROM_LAYER2 = 12
   

class EventList:
    def __init__(self):
        #self.self = self
        self.event_list = []

    def add(self, e):
        #print(e)
        self.event_list.append(deepcopy(e))

    def removeNext(self):
        if len(self.event_list) == 0:
            return None
        #print(len(self.event_list))
        index = 0
        soonest = self.event_list[index].event_time
        
        for i in range(0,len(self.event_list)):
            if self.event_list[i].event_time < soonest:
                soonest = self.event_list[i].event_time
                index = i
        #print(" i ======" + str(i))
        next_event = self.event_list[index]
        self.event_list.pop(index)
        return next_event                    

    def getLastTime(self):
        if len(self.event_list) == 0:
            return None
        index = 0
        largest = self.event_list[index].event_time
        
        for i in range(0,len(self.event_list)):
            if self.event_list[i].event_time > largest:
                largest = self.event_list[i].event_time
                index = i
        return largest
    
    def print_evlist(self):
        for i in range(len(self.event_list)):                        
            e = self.event_list[i]    
            print("Event time: " + str(e.event_time) +
                               ", type: " + str(e.event_type) +
                               " entity: " + str(e.entity))
            
        print("--------------")
