
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

    def stepper(self, step_right, step_down):
        '''Steps through the map staring at ind 0,0 and walking three steps right one down'''
        x_len = len(self.map[0])# 31 
        y_len = len(self.map)   # 323
        tree_crash = 0
        for y in range(0, y_len, step_down):
            x_index = (( int(y/step_down) * step_right)) % x_len
            #print('x {}, y {}, sig {}'.format(x_index, y, self.map[y][x_index]))
            #print(self.map[2][1])
            if self.map[y][x_index] == '#':
                #print('X {}, Y {}, non mod {}'.format(x_index, y, (( int(y/step_down) * step_right))))
                tree_crash += 1
        return tree_crash

    def multiple_slopes(self):
        return  (   self.stepper(1,1),
                    self.stepper(3,1),
                    self.stepper(5,1),
                    self.stepper(7,1),
                    self.stepper(1,2))

if __name__ == '__main__':
    puzzle3 = Day3()
    #print(puzzle3.stepper(7,1))
    res = puzzle3.multiple_slopes()
    print(res)
