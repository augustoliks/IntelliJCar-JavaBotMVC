from flask import Flask
from flask import request
from flask_restful import Api
from flask import render_template
from routes.routes import Controller_get_data
from routes.routes import Controller_set_data
from routes.routes import Controller_set_balance
from routes.routes import Controller_get_history

app = Flask(__name__)
api = Api(app)

api.add_resource(Controller_set_data, "/set/gas=<gas>&bat=<bat>&lat=<lat>&lon=<lon>&tsp=<tsp>&gsm=<gsm>")
api.add_resource(Controller_set_balance, "/set/sal=<sal>&dad=<dad>")

api.add_resource(Controller_get_data, "/get/")
api.add_resource(Controller_get_history, "/get/history")

@app.route("/", methods=['GET'])
def teste():
    return render_template('index.html')

if __name__ == '__main__':
#    app.run()
    app.run(host='0.0.0.0', port=5000, debug=True)


#http://localhost:5000/set/gas=666&bat=666&lat=666&lon=666
