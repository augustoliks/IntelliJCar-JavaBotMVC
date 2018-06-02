from jcar.pop_model.database import DataBase

class GetSet():

    def __init__(self):
        self.database = DataBase()
        self.today = self.database.last_date()[:8]
        self.data = {'gas': None,
                     'bat': None,
                     'lat': None,
                     'lon': None,
                     'gsm': None,
                     'tsp': None
                     }

    def set_data(self, gas, bat, lat, lon, tsp, gsm):
        self.data['gas'] = gas
        self.data['bat'] = bat
        self.data['lat'] = lat
        self.data['lon'] = lon
        self.data['gsm'] = gsm
        self.data['tsp'] = tsp

        date = tsp[:8]
        hour = tsp[8:-2]

        json = { hour : self.data }
        
        self.today = date
        self.database.update_post(date, json)
        self.database.update_post('now', self.data)
       
        return self.data

    def get_history(self, date):
        return self.database.get_doc(date)

    def get_data(self, date, index):
        json = self.database.get_doc(date)
        return json[index]

    def get_history_today(self):
        return self.database.get_doc(self.today)

    def get_data_now(self):
        return self.database.get_doc('now')

    def get_last_date(self):
        return self.database.last_date()
