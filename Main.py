import random


class Person:

    def __init__(self, name: str, age: int = 0, wealth=0):
        self.wealth = wealth
        self.name = name
        self.age = age
        self.adult = self.set_is_adult()
        self.check_wealth()

    def is_adult(self) -> bool:
        """checks if adult or not"""
        if self.adult:
            return True
        else:
            return False

    def set_is_adult(self) -> bool:
        """sets adult boolean to true or false depending on age."""
        if self.age >= 18:
            return True
        else:
            return False

    def check_equal(self, person_b):
        if self == person_b:
            print("{} and {} are equal".format(self.name, person_b.name))
        else:
            print("{} and {} are not equal".format(self.name, person_b.name))

    def check_wealth(self):
        if self.wealth < 0:
            self.wealth = 0

    def __str__(self):
        return "{}: {} years old, {} wealth".format(self.name, self.age, self.wealth)


class Fighter(Person):

    def __new__(cls, name: str, age: int, wealth: int = 0, spear=0, unarmed_combat=0, mace=0, broadsword=0):
        if age < 18:
            print("Fighter cannot be under 18, creating a Person instead")
            return Person(name, age, wealth)
        else:
            return super(Fighter, cls).__new__(cls)

    def __init__(self, name: str, age: int, wealth=0, spear=0, unarmed_combat=0, mace=0, broadsword=0):
        Person.__init__(self, name, age, wealth)
        self.is_adult()
        self.__skills_dict = {'spear': self.check_skill_level(spear), 'unarmed_combat': self.check_skill_level(unarmed_combat), 'mace': self.check_skill_level(mace), 'broadsword': self.check_skill_level(broadsword)}

    def check_skill_level(self, skill: str):
        if skill < 0:
            return 0
        elif skill > 10:
            return 10
        else:
            return skill

    @property
    def skills(self) -> dict:
        """returns the skills of the fighter"""
        return self.__skills_dict

    def challenge(self, fighter: 'Fighter', skill: str) -> None:
        """ This method checks to see if the fighters are eligible to be in a fight by checking wealth and making
        sure the two fighters are not the same. It then initiates the fight. and will send the fight request to the
        fighter if fighter is a warrior/knight """
        if self != fighter:
            if self.wealth > 0:
                if fighter.wealth != 0:
                    self.initiate_fight(fighter, skill)
                else:
                    print("{} does not have enough money to be challenged.".format(fighter.name))
            else:
                print(self.name + " does not have enough money to challenge " + fighter.name)
        else:
            print(self.name + " cannot fight themselves!")

    def initiate_fight(self, fighter: 'Fighter', skill: str):
        fight = Fight(self, fighter, skill)
        if isinstance(fighter, Warrior) or isinstance(fighter, KnightErrant):
            print("{} has challenged {} in the skill of {}, and is waiting for request to be accepted".format(self.name,
                                                                                                              fighter.name,
                                                                                                              skill))
            fighter.challenge_list_add(fight)
        else:
            fight.fight()

    def withdraw(self, fighter: 'Fighter') -> None:
        """If a fighter wants to withdraw, this method goes to the warrior/knight's challenge list and removes the
        challenge. :type fighter: Fighter"""
        fighter.challenge_list_withdraw(self)
        print(self.name + " has withdrew the challenge with " + fighter.name)

    def __str__(self):
        return "{}: {} years old, {} wealth, {}".format(self.name, self.age, self.wealth, self.skills)


class Fight:
    def __init__(self, fighter_a: Fighter, fighter_b: Fighter, skill: str, won: Fighter = None,
                 loser: Fighter = None) -> None:
        self.fighter_a = fighter_a
        self.fighter_b = fighter_b
        self.skill = skill
        self.won = won
        self.loser = loser

    def fight(self) -> None:
        """Starts the fight and finds a winner, then calls complete_battle method to finish the battle"""
        fighter_a_skill = self.fighter_a.skills[self.skill]
        fighter_b_skill = self.fighter_b.skills[self.skill]
        if fighter_a_skill > fighter_b_skill:
            won = self.fighter_a
            loser = self.fighter_b
        elif fighter_a_skill < fighter_b_skill:
            won = self.fighter_b
            loser = self.fighter_a
        else:
            fighter_list = list()
            fighter_list.append(self.fighter_a)
            fighter_list.append(self.fighter_b)
            won = random.choice(fighter_list)
            if won == self.fighter_a:
                loser = self.fighter_b
            elif won == self.fighter_b:
                loser = self.fighter_a
        self.won = won
        self.loser = loser
        self.complete_battle()

    def complete_battle(self) -> None:
        """Calls winner method and then deals with the displaying of the results from the battle."""
        winners_original_wealth = self.won.wealth
        losers_original_wealth = self.loser.wealth
        winners_original_skill = self.won.skills[self.skill]
        print(self.winner().name + " is the winner!")
        print("{} has gained {} wealth".format(self.won.name, (self.won.wealth - winners_original_wealth)))
        print("{} has lost {} wealth".format(self.loser.name, (losers_original_wealth - self.loser.wealth)))
        print("{} has gained {} skill points in {}.".format(self.won.name,
                                                            (self.won.skills[self.skill] - winners_original_skill),
                                                            self.skill))
        print(self.won.name + "'s stats are now: \n\twealth: {} \n\tskills: {}".format(self.won.wealth,
                                                                                       self.won.skills))

    def winner(self) -> Fighter:
        """winner checks the instances of fighters and calculates all of the skill and wealth changes and then
        returns a winner """
        print(self.fighter_a.name + " is fighting " + self.fighter_b.name)
        if type(self.loser) == Warrior and type(self.won) == Fighter:
            self.loser.wealth = self.loser.wealth - 25
            self.won.wealth = self.won.wealth + 25
            self.won.skills[self.skill] = self.won.skills[self.skill] + 1
        elif type(self.loser) == KnightErrant and type(self.won) == Fighter:
            self.loser.wealth = self.loser.wealth - 40
            self.won.wealth = self.won.wealth + 40
            self.won.skills[self.skill] = self.won.skills[self.skill] + 2
        elif type(self.loser) == KnightErrant and type(self.won) == Warrior:
            self.loser.wealth = self.loser.wealth - 20
            self.won.wealth = self.won.wealth + 20
            self.won.skills[self.skill] = self.won.skills[self.skill] + 1
        else:
            self.won.wealth = self.won.wealth + 10
            self.loser.wealth = self.loser.wealth - 10
        pointlist = list()
        pointlist.append(self.fighter_b)
        pointlist.append(self.fighter_a)
        point = random.choice(pointlist)
        point.skills[self.skill] = point.skills[self.skill] + 1
        print("{} has gained a point in battle!".format(point.name))
        if self.loser.wealth < 0:
            self.loser.wealth = 0
        if self.won.skills[self.skill] > 10:
            self.won.skills[self.skill] = 10
        elif self.loser.skills[self.skill] > 10:
            self.loser.skills[self.skill] = 10
        return self.won

    def __str__(self):
        return "{} vs {} skill: {}".format(self.fighter_a.name, self.fighter_b.name, self.skill)


class Warrior(Fighter):

    def __init__(self, name: str, age: int, wealth=0, spear=0, unarmed_combat=0, mace=0, broadsword=0,
                 challenge_list=list()) -> None:
        Fighter.__init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword)
        self.challenge_list = challenge_list

    def accept_random(self) -> None:
        """Randomly chooses a battle from the challenge list and then accepts it. Then removes it from the  and starts the fight."""
        if self.challenge_list:
            battle = random.choice(self.challenge_list)
            print("{} has accepted the challenge from {}".format(self.name, battle.fighter_a.name))
            self.challenge_list.remove(battle)
            battle.fight()
        else:
            print("{} has no challenges to accept".format(self.name))

    def decline_random(self) -> None:
        """Randomly chooses a battle from the challenge list and then declines it. Then removes it from list."""
        if self.challenge_list:
            battle = random.choice(self.challenge_list)
            print("{} has declined the challenge from {}".format(self.name, battle.fighter_a.name))
            self.challenge_list.remove(battle)
        else:
            print("{} has no challenges to decline".format(self.name))

    def accept_first(self) -> None:
        """Accepts the first challenge from the challenge list, removes it and then starts the fight."""
        if self.challenge_list:
            battle = self.challenge_list[0]
            print("{} has accepted the challenge from {}".format(self.name, battle.fighter_a.name))
            self.challenge_list.remove(battle)
            battle.fight()
        else:
            print("{} has no challenges to accept".format(self.name))

    def decline_first(self) -> None:
        """Declines first battle from the challenge list and then declines it. Then removes it from list."""
        if self.challenge_list:
            battle = self.challenge_list[0]
            print("{} has declined the challenge from {}".format(self.name, battle.fighter_a.name))
            self.challenge_list.remove(battle)
        else:
            print("{} has no challenges to decline".format(self.name))

    def challenge_list_add(self, battle: Fight) -> None:
        """Adds challenge to list of challenges"""
        self.challenge_list.append(battle)

    def challenge_list_withdraw(self, challenger: Fighter) -> None:
        """This method is called when a Fighter withdraws its challenge. This looks for the fighter in the list and
        removes the battle."""
        for e in self.challenge_list:
            if e.fighter_a == challenger:
                self.challenge_list.remove(e)

    def __str__(self) -> str:
        return '{}: {} years old, {} wealth, {}, challenges: {}'.format(self.name, self.age,
                                                                        self.wealth, self.skills,
                                                                        self.challenge_list)


class KnightErrant(Warrior):

    def __init__(self, name: str, age: int, wealth: int = 0, spear: int = 0, unarmed_combat: int = 0, mace: int = 0,
                 broadsword: int = 0,
                 challenge_list: list = list(), traveling: bool = False) -> None:
        Warrior.__init__(self, name, age, wealth, spear, unarmed_combat, mace, broadsword, challenge_list)
        self.traveling = traveling

    def travel(self) -> None:
        """Sets Knights traveling to true"""
        print(self.name + " is traveling")
        self.traveling = True

    def return_from_travel(self) -> None:
        """Knight is returning from traveling. A treasure from a list of possible treasures is randomly chosen and is
        added to Knights wealth. """
        print(self.name + " has returned")
        treasure_list = [0, 5, 10, 20, 40]
        treasure = random.choice(treasure_list)
        self.wealth = self.wealth + treasure
        print("{} found {} wealth points on his journey!".format(self.name, treasure))
        self.traveling = False

    def accept_random(self) -> None:
        """Randomly chooses a battle from the challenge list and then accepts it. Then removes it from the  and starts the fight."""
        if not self.traveling:
            if self.challenge_list:
                battle = random.choice(self.challenge_list)
                print("{} has accepted the challenge from {}".format(self.name, battle.fighter_a.name))
                self.challenge_list.remove(battle)
                battle.fight()
            else:
                print("{} has no challenges to accept".format(self.name))
        else:
            print("{} can not accept challenges when traveling.".format(self.name))

    def decline_random(self) -> None:
        """Randomly chooses a battle from the challenge list and then declines it. Then removes it from list."""
        if not self.traveling:
            if self.challenge_list:
                battle = random.choice(self.challenge_list)
                print("{} has declined the challenge from {}".format(self.name, battle.fighter_a.name))
                self.challenge_list.remove(battle)
            else:
                print("{} has no challenges to decline".format(self.name))
        else:
            print("{} can not decline challenges when traveling.".format(self.name))

    def accept_first(self) -> None:
        """Accepts the first challenge from the challenge list, removes it and then starts the fight."""
        if not self.traveling:
            if self.challenge_list:
                battle = self.challenge_list[0]
                print("{} has accepted the challenge from {}".format(self.name, battle.fighter_a.name))
                self.challenge_list.remove(battle)
                battle.fight()
            else:
                print("{} has no challenges to accept".format(self.name))
        else:
            print("{} can not accept challenges when traveling.".format(self.name))

    def decline_first(self) -> None:
        """Declines first battle from the challenge list and then declines it. Then removes it from list."""
        if not self.traveling:
            if self.challenge_list:
                battle = self.challenge_list[0]
                print("{} has declined the challenge from {}".format(self.name, battle.fighter_a.name))
                self.challenge_list.remove(battle)
            else:
                print("{} has no challenges to decline".format(self.name))
        else:
            print("{} can not decline challenges when traveling.".format(self.name))

    def challenge(self, fighter: 'Fighter', skill: str) -> None:
        """ This method checks to see if the fighters are eligible to be in a fight by checking wealth and making
        sure the two fighters are not the same. It then initiates the fight. and will send the fight request to the
        fighter if fighter is a warrior/knight """
        if self != fighter:
            if self.wealth > 0:
                if fighter.wealth != 0:
                    if not self.traveling:
                        self.initiate_fight(fighter, skill)
                    else:
                        print("{} cannot challenge when traveling. ".format(self.name))
                else:
                    print("{} does not have enough money to be challenged.".format(fighter.name))
            else:
                print(self.name + " does not have enough money to challenge " + fighter.name)
        else:
            print(self.name + " cannot fight themselves!")

    def __str__(self):
        return "{}: {} years old, {} wealth, {}, challenges pending: {}, traveling: {} ".format(self.name, self.age,
                                                                                                self.wealth,
                                                                                                self.skills,
                                                                                                len(
                                                                                                    self.challenge_list),
                                                                                                self.traveling)
