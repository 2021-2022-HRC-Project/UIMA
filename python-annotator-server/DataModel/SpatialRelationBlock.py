from dataclasses import dataclass


@dataclass
class SpatialRelationBlock:
    block_id: int
    name: str
    x: float
    y: float
    z: float
    left: dict
    right: dict
    front: dict
    behind: dict

    def convertStrToDict(self, relation: str) -> dict:
        # "[(2,0.991050002470602), (3,0.9894400957775169), (4,0.9816571955966872),
        #       (5,0.984936306729746)]"
        return_dict: dict = {}
        block_dict = relation.replace('[', '').replace(']', '').replace('(', '').split('),')
        for block in block_dict:
            if block != '':
                block = block.replace(')', '')
                temp = block.split(',')
                return_dict[int(temp[0])] = float(temp[1])
        return return_dict

    def __init__(self, block_id: int,
                 name: str,
                 x: float,
                 y: float,
                 z: float,
                 left: str,
                 right: str,
                 front: str, behind: str):
        self.block_id = block_id
        self.name = name
        self.x = x
        self.y = y
        self.z = z
        self.left = self.convertStrToDict(left)
        self.right = self.convertStrToDict(right)
        self.behind = self.convertStrToDict(behind)
        self.front = self.convertStrToDict(front)
