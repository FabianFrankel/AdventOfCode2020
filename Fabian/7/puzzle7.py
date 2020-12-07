


class bag_rec:

    def __init__(self, input_array):
        self.rules = []
        for i in input_array:
            if('no other bags' in i.split('contain')[1]):
                self.rules.append([i.split('contain')[0], []])
            else:
                self.rules.append([i.split('contain')[0], i.split('contain')[1].split(',')])
        self._clean_input()

    def _clean_input(self):
        new_rules = []
        for i in self.rules:
            temp_bag = i[0][:-2]
            inner_bags = {}
            for j in i[1]:
                if( j[1] > 1):
                    inner_bags.update({j[3:][:-1] : j[1]})
                else:
                    inner_bags.update({j[3:] : j[1]})
            new_rules.append([temp_bag, inner_bags])
        self.rules = new_rules

    def print_rules(self):
        print(self.rules)   


    def can_bag_carry_bag(self, outerbag, inner_bag):

        bag_rules = self.get_bag_rules(outerbag)
        print(bag_rules)
        resulting_dict = {}
        if not bag_rules:
            return False
        for i in bag_rules.keys():
            if(inner_bag == i):
                resulting_dict.update({i: True})
            else:
                resulting_dict.update({i: self.can_bag_carry_bag(i, inner_bag)})
        if(True in resulting_dict.values()):
            return True
        return False

    def get_bag_rules(self, bag):
        for i in self.rules:
            if bag == i[0]:
                return i[1]
        return {}

    def number_of_outer_bags_for_bag(self, bag):
        nbr_outer_bags = 0
        print(self.rules)
        for i in self.rules:
            print(i[0], bag)
            if self.can_bag_carry_bag(i[0], bag):
                nbr_outer_bags = nbr_outer_bags +1
        return nbr_outer_bags






def get_input_array():
    input_array = []
    with open('input_test.txt') as my_file:
        for line in my_file:
            line = line.replace('\n','').replace('.', '')
            input_array.append(line)
    return input_array




if __name__ == '__main__':
    input_array = get_input_array()
    bag_rec = bag_rec(input_array)
    print(bag_rec.number_of_outer_bags_for_bag('shiny gold bag'))
    ##bag_rec.print_rules()
    