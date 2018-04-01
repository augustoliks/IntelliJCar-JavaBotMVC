from flask_restful import Resource
from flask import jsonify
from jcar.pop_model.get_set import GetSet

getter_setter = GetSet()

class Controller_get_data(Resource):
    def get(self):
        return jsonify( getter_setter.get_data() )

class Controller_set_data(Resource):
    def get(self, gas, bat, lat, lon, tsp, gsm):
        return jsonify( getter_setter.set_data(gas, bat, lat, lon, tsp, gsm) )

class Controller_set_balance(Resource):
    def get(self, sal, dad):
        return jsonify( getter_setter.set_balance(sal, dad) )

class Controller_get_history(Resource):
    def get(self):
        return jsonify( getter_setter.get_history() )
