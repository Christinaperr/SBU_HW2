from Warrior import Warrior
import random

class KnightErrant(Warrior):

    def __init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword, challenge_list, traveling):
        Warrior.__init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword, challenge_list)
        self.traveling = traveling

    def travel(self):
        print(self.name + " is traveling")
        self.traveling = True

    def return_from_travel(self):
        print(self.name + " has returned")
        treasure_list = [0, 5, 10, 20, 40]
        treasure = random.choice(treasure_list)
        self.wealth = self.wealth + treasure
        print(self.name + " found " + treasure + " wealth points!")
        self.traveling = False