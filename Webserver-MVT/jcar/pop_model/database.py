import pymongo
from bson import json_util, ObjectId
import json

class DataBase():
    def __init__(self):

        self.client = pymongo.MongoClient('localhost', 27017)
        self.db = self.client['jcar']
        self.collections = self.db['date']
    
    def update_post(self, date, post):
        self.collections.update(
            {"_date" : date},
            { "$set" : post},
            upsert=True
        )

    def get_doc(self, date):
        try:
            pymongo_object = self.collections.find( {'_date': date } )
            json_data = pymongo_object[0]
            json_data = json.loads(json_util.dumps(json_data))
            
        except:
            return None
        
        print(json)
        
        json_data.pop("_date")
        json_data.pop("_id")
        
        return json_data

    def last_date(self):
        try:
            pymongo_object = self.collections.find( {'_date': 'now'} )
            json_data = pymongo_object[0]
            json_data = json.loads(json_util.dumps(json_data))
            
        except:
            return None
            
        return json_data['tsp']