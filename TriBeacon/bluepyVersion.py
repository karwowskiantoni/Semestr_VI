from datetime import datetime
from bluepy.btle import Scanner
import requests


def send_to_server():
    URL = "https://tri-beacon.herokuapp.com/users"
    response = requests.get(URL)
    body = response.json()
    return response.status_code


def scan():
    devices = Scanner().scan(7.0)
    names = []
    for device in devices:
        for (_, description, value) in device.getScanData():
            if description == "Complete Local Name":
                names.append(value)
    return names


while True:
    print(datetime.now().strftime("[%H:%M:%S]:"), scan(), ", response from server: ", send_to_server())
