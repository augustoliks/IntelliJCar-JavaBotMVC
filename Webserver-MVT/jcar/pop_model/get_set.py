from jcar.utils.utils import Util
import ast
class GetSet():

    def __init__(self):
        self.data = {'gas': None,
                     'bat': None,
                     'lat': None,
                     'lon': None,
                     'sal': None,
                     'dad': None,
                     'tsp': None
                     }

    def read_json(self):
        load = open('database/informations.json')
        self.data = ast.literal_eval( load.read() )

    def write_json(self):
        json = open ('database/informations.json', 'w')
        json.write( str(self.data) )
        json.close()

    def set_data(self, gas, bat, lat, lon, tsp):
        self.read_json()

        self.data ['gas'] = gas
        self.data ['bat'] = bat
        self.data ['lat'] = lat
        self.data ['lon'] = lon
        self.data ['tsp'] = tsp

        self.write_json()

        return self.data

    def get_data(self):
        self.read_json()
        return self.data

    def set_balance(self, sal, dad):
        self.read_json()

        self.data ['sal'] = sal
        self.data ['dad'] = dad

        self.write_json()

        return self.data
