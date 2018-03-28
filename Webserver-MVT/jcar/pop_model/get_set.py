from jcar.utils.utils import Util

class GetSet():

    def __init__(self):
        self.data = {}

    def read_json(self):
        load = open('database/informations.json')
        return load.read()

    def write_json(self, data):
        json = open ('database/informations.json', 'w')
        json.write(str(data))
        json.close()

    def set_data(self, gas, bat, lat, lon):
        self.data = {"gas": gas,
                "bat": bat,
                "lat": lat,
                "lon": lon
                }

        self.write_json(self.data)
        return self.read_json()

    def get_data(self):
        return self.read_json()
