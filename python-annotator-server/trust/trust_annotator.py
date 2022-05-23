from numpy import diff
import nltk
import json
from nltk.stem import WordNetLemmatizer 

sentence = CAS.task
jsonObjs = CAS.commandItems
importance = CAS.importance
word_list = nltk.word_tokenize(sentence)

lemmatizer = WordNetLemmatizer()
lemmatized_output = (' '.join([lemmatizer.lemmatize(w) for w in word_list])).lower().strip()

conjunctions = ["also", "although", "and", "as", "because", "before", "but", "for", "if", "nor", "of", "or", "since", "that", "though", "until", "when", "whenever", "whereas", "which", "while", "yet"]

def findDifficulty(sentence):
    if sentence:
        count_dificulty = 0
        for word in word_list:
            if word in conjunctions:
                count_dificulty += 1
                print(word)
        return count_dificulty/len(word_list)

def findJSON():
    if lemmatized_output:
        for obj in jsonObjs:
            if obj["key"] == lemmatized_output:
                return obj

difficulty = findDifficulty(lemmatized_output)
jsonObjs["commands"]
def calculateConfidence():
    if lemmatized_output in jsonObjs["commands"].keys():
        commandData = jsonObjs["commands"][lemmatized_output]
        weightedAve = 0
        importanceCount = 0
        print(commandData)
        for i in range(len(commandData["successes"])):
            weightedAve += (commandData["successes"][i]*commandData["importance"][i])
            importanceCount += commandData["importance"][i]
        return(weightedAve/importanceCount)
    else:
        return 1

def addJSON(jsonObjs, key, success, importance, difficulty):
    newJSON = json.dumps({
        "key": lemmatized_output,
        "performed": 1,
        "successes": [1],
        "importance": importance,
        "difficulty": findDifficulty(lemmatized_output)
    })
    jsonObjs["commands"].append(newJSON)

def updateJSON():
    jsonCommands = jsonObjs["commands"]
    matchingObj = jsonCommands[lemmatized_output]
    matchingObj["performed"] += 1
    matchingObj["importance"].append(importance)

def getOutput():
    output = ""
    ask = True
    if lemmatized_output in jsonObjs["commands"].keys():
        confidence = calculateConfidence()
        difficulty = findDifficulty()
        updateJSON()
        if confidence >= 0.9:
            if difficulty >= 0.2:
                ask = False
        elif confidence >= 0.7:
            if difficulty >= 0.1:
                ask = False
        elif confidence >= 0.5:
            if difficulty < 0.1:
                ask = False
        else:
            output = "My confidence in my ability is "+ confidence +". Should I proceed?"
    return output
def main():


if __name__ == '__main__':
    main()
