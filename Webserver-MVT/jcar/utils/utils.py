import collections
import ast

class Util():

    def __init__(self, start, end):
        self.json = self.read_json()
        self.start = start
        self.end = end
        self.json_cut={}

    def debug(self):
        print(self.json_cut.items())
        for k in self.json_cut:
            print(k)

    def read_json(self):
        _load = open('database/history.json')
        _json = ast.literal_eval( _load.read() )
        return collections.OrderedDict(sorted(_json.items()))

    def validate_utc(self, utc):
        if (len(utc)!=4):
            return False

        if ( utc.isdecimal() == False ):
            return False

        if (int (utc[0:2]) > 24 or int (utc[2::]) > 59):
            return False

        return True


    def cut_json(self):
        if (int (self.start) < int(self.end) and ( self.validate_utc(self.start) == True and self.validate_utc(self.end) == True) ):

            flag = False
            for k in self.json:
                print(k)

            for k in self.json:
                if (k == self.start):
                    flag = True

                if (k == self.end):
                    print('acabou '+k)
                    break

                if (flag == True):
                    print('entrou: '+k)
                    self.json_cut[k] = self.json[k]

        else:
            self.json_cut = 'ERRO'

'''
    def utc_to_br(utc_hm):
        utc_h = int(utc_hm)
        br_h = ""
        if utc_h - 3 <= 0:
            if utc_h == 0:
                br_h = '21'
            elif utc_h == 1:
                br_h = '22'
            elif utc_h == 2:
                br_h = '23'
        else:
            if utc_h <= 12 and utc_h > 2:
                  br_h = '0'
                  br_h += str(utc_h - 3)
            else:
                  br_h = str(utc_h - 3)
        return br_h+utc_hms[:-2]
'''
