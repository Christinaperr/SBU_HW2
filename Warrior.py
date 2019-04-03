from Fighter import Fighter
import random

class Warrior(Fighter):

    def __init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword, challenge_list):
        Fighter.__init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword)
        self.challenge_list = challenge_list

    def accept_random(self):
        challenge = random.choice(self.challenge_list)
        print(self.name + " has accepted " + challenge)

    def decline_random(self):
        challenge = random.choice(self.challenge_list)
        print(self.name + " has declined the challenge " + challenge)


    def accept_first(self):
        challenge = self.challenge_list[0]
        print(self.name + " has accepted " + challenge)

    def challenge_list_add(self, challenger):
        self.challenge_list = self.challenge_list.append(challenger)