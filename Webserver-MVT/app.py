from flask import Flask, render_template, request
from flask_restful import Api
from routes.controller import Controller
from routes.routes import *
from jcar.utils.toolbox import ToolboxJson
from jcar.pop_model.database import DataBase

#app.jinja_env.line_statement_prefix = '$'
app = Flask(__name__)
api = Api(app)

controller = Controller()

#set data
api.add_resource(Controller_set_data,"/set/gas=<gas>&bat=<bat>&lat=<lat>&lon=<lon>&tsp=<tsp>&gsm=<gsm>")

#by hour
api.add_resource(Controller_get_data,"/get/data/date=<date>&index=<index>")
api.add_resource(Controller_get_data_now,"/get/data/now")

#by day
api.add_resource(Controller_get_history,"/get/history/date=<date>")
api.add_resource(Controller_get_history_today,"/get/history/today")


@app.route("/", methods=['GET'])
def all_points():
    icon = open('static/logo.png', 'rb')
    
    today = controller.get_last_date() 
    json = controller.get_history(today[:8])

    return render_template('index.html', json = json, icon = icon)

@app.route("/onepoint", methods=['GET'])
def one_point():
    icon = open('static/logo.png', 'rb')

    date = request.args.get("date")
    index = request.args.get("index")
    
    json = {}

    if (date == 'today' and index == 'now'):
        json = {'now' : controller.get_data_now()}

    elif (date == 'today'):
        today = controller.get_last_date()
        json = {'today' : controller.get_data(today[:8], index)}
    
    else:
        json = {date : controller.get_data(date, index)}
        
    return render_template('index.html', json = json, icon = icon)

@app.route("/route", methods=['GET'])
def some_points():
    icon = open('static/logo.png', 'rb')

    date = request.args.get("date")
    start = request.args.get("start")
    end = request.args.get("end")

    json = controller.get_history(date)
    json_new = ToolboxJson(json, start, end)

    json_new.cut_json()

    if (json_new.json_cut != 'ERRO'):
        return render_template('index.html', json = json_new.json_cut, icon = icon)
    else:
        return 'Set start and end hour valide\nCorrect Format example: start=1010 end=2020'


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)

#http://localhost:5000/route?date=20183111&start=1010&end=2020

#http://localhost:5000/set/gas=666&bat=666&lat=666&lon=666&tsp=20183112000199&gsm=123
#http://localhost:5000/route?start=1010&end=2020

#http://localhost:5000/get/history/date=20180408&index=0007