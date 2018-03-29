from json import dumps
from flask_restful import Resource
#from flask.ext.jsonpify import jsonify

from jcar.pop_model.get_set import GetSet

getter_setter = GetSet()

class Controller_get_data(Resource):
    def get(self):
        return getter_setter.get_data()

class Controller_set_data(Resource):
    def get(self, gas, bat, lat, lon):
        return getter_setter.set_data(gas, bat, lat, lon)

class Controller_set_balance(Resource):
    def get(self, sal, dad):
        return getter_setter.set_balance(sal, dad)
