from flask import Flask, abort, request
import json

app = Flask(__name__)

def read_json():
    load = open("information.json")
    return load.read()

def write_json(data):
    json = open ('information.json', 'w')
    json.write(str(data))
    json.close()

@app.route("/get", methods=['GET'])
def get_json():
    return json.dumps( read_json() )

@app.route("/set", methods=['GET'])
def set_json():
    latitude = request.args.get("lat")
    longitude = request.args.get("lon")
    gas_nivel = request.args.get("gas")
    bat_nivel = request.args.get("bat")

    json_aux = {"lat:": latitude,
                "lon": longitude,
                "gas": gas_nivel,
                "bat": bat_nivel
                }
    write_json(json_aux)
    return json.dumps(json_aux)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)

#http://localhost:5000/set?lat=10&lon=11&gas=12&bat=13
