import collections
import ast

class ToolboxJson():

    def __init__(self, json, start, end):
        self.json = self.ordenate_json(json)
        self.start = start
        self.end = end
        self.json_cut={}

    def ordenate_json(self, json):
        return collections.OrderedDict(sorted(json.items()))

    def validate_utc(self, utc):
        if (len(utc)!=4):
            return False

        if ( utc.isdecimal() == False ):
            return False

        if (int (utc[0:2]) > 24 or int (utc[2::]) > 59):
            return False

        return True

    def cut_json(self):
        if (int (self.start) < int(self.end) and 
            (self.validate_utc(self.start) == True and 
            self.validate_utc(self.end) == True) ):

            flag = False

            for k in self.json:
                if (k == self.start):
                    flag = True

                if (k == self.end):
                    break

                if (flag == True):
                    self.json_cut[k] = self.json[k]

        else:
            self.json_cut = 'ERRO'