import random

from Fight import Fight
from Person import Person
from Warrior import Warrior
from KnightErrant import KnightErrant


class Fighter(Person):
    skills_dict = {
        "spear": 0,
        "unarmed combat": 0,
        "mace": 0,
        "broadsword": 0
    }

    def __init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword):
        Person.__init__(self, name, age, wealth)
        self.set_is_adult(self)
        self.skills_dict["spear"] = spear
        self.skills_dict["unarmed combat"] = unarmed_combat
        self.skills_dict["mace"] = mace
        self.skills_dict["broadsword"] = broadsword

    def challenge(self, fighter, skill):
        if self != fighter:
            if self.wealth > 0:
                if fighter is isinstance(Warrior) or fighter is isinstance(KnightErrant):
                    fighter.challenge_list_add(fighter, self)
                else:
                    self.check_challenge(fighter, self, skill)
            else:
                print(self.name + " does not have enough money to challenge " + fighter.name)
        else:
            print(self.name + " cannot fight themselves!")

    def check_challenge(self, initiator, skill):
        if self.wealth != 0:
            self.check_skill(self, initiator, skill)

    def check_skill(self, fighter, skill):
        fighter_a = self.skills_dict[skill]
        fighter_b = fighter.skills_dict[skill]
        if fighter_a > fighter_b:
            print(self.name + " wins the fight")
            winner = self
            loser = fighter
        elif fighter_a < fighter_b:
            print(fighter.name + "wins the fight")
            winner = fighter
            loser = self
        else:
            winner = random.choices(self, fighter)
            if winner == self:
                loser = fighter
            elif winner == fighter:
                loser = self
            fight = Fight(self, winner, loser, skill)
       #     Fight.__init__(self, winner, loser, skill)


 #   def withdraw(self):


    def __str__(self):
        print()
