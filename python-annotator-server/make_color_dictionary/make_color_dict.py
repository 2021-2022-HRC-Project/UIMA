import json

color_dict = {}

with open("colornames.json", encoding='utf-8') as f:
    data = json.load(f)

for entry in data:
    h = entry['hex'].lstrip('#')
    color_dict[entry['name'].lower()] = tuple(int(h[i:i+2], 16) for i in (0, 2 ,4))

json = json.dumps(color_dict)
f = open("color_dictionary.json","w")
f.write(json)
f.close()