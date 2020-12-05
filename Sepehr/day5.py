class Day5:

    def __init__(self):
        self.seat_dict = dict()
        self.max_id = 0
        self._read_data()

    def _read_data(self):
        '''Reads the input data and inserts seat ID as key, and code string
        as value in the dict seat_dict'''
        seat_text = open('inputs/input5.txt', 'r')
        for seat_code in seat_text.readlines():
            # Removing new line char in end of line
            seat_code = seat_code[:-1]
            seat_id = self._id_calculator(seat_code)
            self.seat_dict[seat_id] = seat_code

    def _id_calculator(self, bin_code):
        '''Finds the correct row and col position of a seat.
        Takes in the code in form: FFBFBFFRLL, calculates the ID and returns it'''
        row_num = list(range(0,128))
        col_num = list(range(0,8))
        for letter in bin_code[:7]:
            row_num = self._row_selecter(letter, row_num)
            #print(row_num, "row")
        for letter in bin_code[-3:]:
            col_num = self._col_selecter(letter, col_num)
            #print(col_num, "col")
        id = row_num[0] * 8 + col_num[0]
        if id > self.max_id: self.max_id = id
        return id
        
    
    def _row_selecter(self, letter, seat_list):
        '''Takes in the one letter of the boarding pass, and possible positions in seat_list
        If code is F, takes the lower half of the possible seats
        If code is B, takes the upper half of the possible seats.
        Returns the final row number as a list with one element'''

        if      letter == 'F': return seat_list[:int(len(seat_list)/2)]
        elif    letter == 'B': return seat_list[int(len(seat_list)/2):]
    
    def _col_selecter(self, letter, seat_list):
        '''Takes in the code letter, and possible positions in seat_list
        If code is R, takes the upper half of the possible seats
        If code is L, takes the upper lower of the possible seats.
        Returns the final col number as a list with one element'''
        if letter == 'R': return seat_list[int(len(seat_list)/2):]
        elif letter == 'L': return seat_list[:int(len(seat_list)/2)]

    def print(self):
        '''Prints key:value pair in dict seat_dict'''
        for key, value in self.seat_dict.items():
            print(key, "\t:", value)

    def get_max(self):
        '''Returns the maximum ID'''
        return self.max_id

    def get_my_seat(self):
        '''Returns my seat ID'''
        key_list = list(self.seat_dict)
        key_list.sort()
        for i in range(len(key_list) - 1):
            if key_list[i+1] - key_list[i] != 1:
                return key_list[i]+1

if __name__ == '__main__':
    puzzle = Day5()
    puzzle.print()
    my_seat = puzzle.get_my_seat()
    print(my_seat)

