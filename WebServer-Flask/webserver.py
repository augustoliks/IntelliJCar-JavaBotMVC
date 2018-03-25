from flask import Flask, abort, request

app = Flask(__name__)

def read_json():
    load = open("informations.json")
    return load.read()

def write_json(data):
    json = open ('informations.json', 'w')
    json.write(str(data))
    json.close()

@app.route("/get", methods=['GET'])
def get_json():
    return read_json()

@app.route("/set", methods=['GET'])
def set_json():
    lat = request.args.get("lat")
    lon = request.args.get("lon")
    gas = request.args.get("gas")
    bat = request.args.get("bat")
    data = {}
    data = {"lat": lat,
            "lon": lon,
            "gas": gas,
            "bat": bat
            }
    write_json(data)
    return read_json()

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)

#http://localhost:5000/set?lat=10&lon=11&gas=12&bat=13
