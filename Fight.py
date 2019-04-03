from Fighter import Fighter
from Warrior import Warrior
from KnightErrant import KnightErrant
import random


class Fight:
    def __init__(self, winner, loser, skill):
        self.loser = loser
        self.winner = winner
        self.skill = skill
        winner(winner)

    def winner(self):
        if isinstance(self.loser, Warrior) and isinstance(self.winner, Fighter):
            self.loser.wealth = self.loser.wealth - 25
            self.winner.wealth = self.winner.wealth + 25
            self.winner.skills_dict[self.skill] = self.winner.skills_dict[self.skill] + 1
        elif isinstance(self.loser, KnightErrant) and isinstance(self.winner, Fighter):
            self.loser.wealth = self.loser.wealth - 40
            self.winner.wealth = self.winner.wealth + 40
            self.winner.skills_dict[self.skill] = self.winner.skills_dict[self.skill] + 2
        elif isinstance(self.loser, KnightErrant) and isinstance(self.winner, Warrior):
            self.loser.wealth = self.loser.wealth - 20
            self.winner.wealth = self.winner.wealth + 20
            self.winner.skills_dict[self.skill] = self.winner.skills_dict[self.skill] + 1
        else:
            self.winner.wealth = self.winner.wealth + 10
            self.loser.wealth = self.loser.wealth - 10
            point = random.choices(self.winner, self.loser)
            point.skills_dict[self.skill] = point.skills_dict[self.skill] + 1
        if self.loser.wealth < 0:
            self.loser.wealth = 0;
        if self.winner.skills_dict[self.skill] > 10:
            self.winner.skills_dict[self.skill] = 10
        return self.winner
