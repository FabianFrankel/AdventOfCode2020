
class Day3:

    def __init__(self):
        self.map = self._format_data()

    def _format_data(self):
        ''' 
        Takes the data and transforms to array matrix
        Following structure: [[a, a, a], ..., [a, a, a]]
        '''
        input = open('inputs/input3.txt', 'r').read()
        # Strip tha last one since it is empty
        map = input.split('\n')[:-1]
        return map

    def stepper(self):
        '''Steps through the map staring at ind 0,0 and walking three steps right one down'''
        x_len = len(self.map[0])# 31 
        y_len = len(self.map)   # 323
        tree_crash = 0
        step_right = 3
        #step_down = 1
        for i in range(y_len):
            x_index = ((i * step_right)) % x_len
            if self.map[i][x_index] == '#':
                tree_crash += 1
        return tree_crash

if __name__ == '__main__':
    puzzle3 = Day3()
    puzzle3.stepper()
