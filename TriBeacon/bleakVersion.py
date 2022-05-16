import requests
from bleak import BleakScanner
import asyncio


async def scan():
    while True:
        devices = await BleakScanner.discover(timeout=7.0)
        for d in devices:
            print(d.name)

        URL = "https://tri-beacon.herokuapp.com/users"
        r = requests.get(URL)
        print(r.json())


if __name__ == '__main__':
    asyncio.run(scan())
