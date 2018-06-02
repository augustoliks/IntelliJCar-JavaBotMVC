from flask_restful import Resource
from flask import jsonify
from jcar.pop_model.get_set import GetSet

getter_setter = GetSet()

#Set's
class Controller_set_data(Resource):
    def get(self, gas, bat, lat, lon, tsp, gsm):
        return jsonify( getter_setter.set_data(gas, bat, lat, lon, tsp, gsm) )

#Get Most Recent
class Controller_get_history(Resource):
    def get(self, date):
        return jsonify( getter_setter.get_history(date) )

class Controller_get_data(Resource):
    def get(self, date, index):
        return jsonify( getter_setter.get_data(date, index) )

#Get By Date
class Controller_get_data_now(Resource):
    def get(self):
        return jsonify( getter_setter.get_data_now() )

class Controller_get_history_today(Resource):
    def get(self):
        return jsonify( getter_setter.get_history_today() )

