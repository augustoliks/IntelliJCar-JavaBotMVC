from flask import Flask
from flask import render_template
from flask import request
from flask_restful import Api
from routes.routes import Controller_get_data
from routes.routes import Controller_set_data
from routes.routes import Controller_set_balance
from routes.routes import Controller_get_history
from routes.routes import Controller_get_id
import ast

app = Flask(__name__)
#app.jinja_env.line_statement_prefix = '$'
api = Api(app)

api.add_resource(Controller_set_data, "/set/gas=<gas>&bat=<bat>&lat=<lat>&lon=<lon>&tsp=<tsp>&gsm=<gsm>")
api.add_resource(Controller_set_balance, "/set/sal=<sal>&dad=<dad>")

api.add_resource(Controller_get_data, "/get/")
api.add_resource(Controller_get_history, "/get/history")

api.add_resource(Controller_get_id, "/get/id=<id>")

@app.route("/", methods=['GET'])
def main():
    load = open('database/history.json')
    json = ast.literal_eval( load.read() )

    icon = open('static/stallman.png', 'rb')

    return render_template('index.html', json = json, icon = icon)


@app.route("/test_map", methods=['GET'])
def test_map():
    load = open('database/history.json')
    json = ast.literal_eval( load.read() )
    return render_template('test.html', json = json)


if __name__ == '__main__':
#    app.run()
    app.run(host='0.0.0.0', port=5000, debug=True)


#http://localhost:5000/set/gas=666&bat=666&lat=666&lon=666
