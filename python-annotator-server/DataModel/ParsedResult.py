import json


class ParsedResult:

    def __init__(self, seq_num):
        with open("../NLPAnnotator/JSONOutput/outputJson" + seq_num + ".json",
                  encoding='utf-8') as f:  # open the NLPOutpu json file
            result = json.load(f)
        nlp_result = result["edu.rosehulman.aixprize.pipeline.types.NLPProcessor"]
        self.command = nlp_result['Command']
        self.target = TargetModel(nlp_result['Target'])
        self.naming = nlp_result["naming"]
        self.receiver = nlp_result["receiver"]
        self.clarificationModel = ClarificationModel(nlp_result["clarificationModel"])


class ItemModel:
    def __init__(self, item_result):
        self.item = item_result["item"]
        self.mods = item_result["mods"]
        self.gesture = item_result["gesture"]
        self.belonging = item_result["belonging"]
        self.id = -1
        self.color = ""

    def to_string(self):
        str1 = " "
        return str1.join([self.item, str(self.mods), str(self.gesture), self.belonging])


class TargetModel(ItemModel):
    def __init__(self, target_result):
        super().__init__(target_result)
        self.relationModel = RelationModel(target_result['relationModel'])


class ClarificationModel:
    def __init__(self, clarification_model):
        self.needCommand = clarification_model["needCommand"]
        self.needTarget = clarification_model["needTarget"]
        self.needReference = clarification_model["needReference"]


def get_items(objects):
    item_list = []
    for item in objects:
        item_list.append(ItemModel(item))
    return item_list


class RelationModel:
    direction: str
    objects: list

    def __init__(self, relation_model):
        self.direction = relation_model["Direction"]
        self.objects = get_items(relation_model["Objects"])
