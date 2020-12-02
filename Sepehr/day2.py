
class Day2: 
    def __init__(self, file_name):
        self.data_dict = self._format_data(file_name)

    def _format_data(self, file_name):
        """Takes in the file name to be used and converts it to a dict"""

        input = open('inputs/input2.txt', 'r').read()
        data_lines = input.split('\n')
        res = dict()
        for i, row in enumerate(data_lines):
            if not row:
                break
            temp = row.split()
            # format is '9-11 a: awdadadadaw'
            res[i] = {  'Min': int(temp[0].split('-')[0]),
                        'Max': int(temp[0].split('-')[1]),
                        'Letter': temp[1][0],
                        'Password': temp[2] 
                        }
        return res

    def password_checker_part1(self):
        '''Check if the password in dataframe are legit and returns how many are valid'''
        # Kopierar för orkar inte skriva massa selfs sen
        valid_counter = 0
        
        for data in self.data_dict.values(): 
            letter_count = 0
            for letter in data['Password']:
                if letter == data['Letter']:
                    letter_count += 1
                elif letter_count > data['Max']:
                    break
            if letter_count <= data['Max'] and letter_count >= data['Min']:
                valid_counter += 1
                #print(valid_counter, letter_count, data['Max'], data['Min'])

        return valid_counter

    def password_checker_part2(self):
        valid_counter = 0
        
        for data in self.data_dict.values():
            if ((data['Password'][data['Min'] - 1] is data['Letter']) ^ 
                (data['Password'][data['Max'] - 1] is data['Letter'])):
                valid_counter += 1
        return valid_counter



if __name__ == '__main__':
    do = 'this'
    puzzle = Day2('input2.txt')
    res1 = puzzle.password_checker_part1()
    res2 = puzzle.password_checker_part2()