class Day5:

    def __init__(self):
        self.seat_dict = dict()
        self.max_id = 0
        self._read_data()
    

    def _read_data(self):
        seat_text = open('inputs/input5.txt', 'r')
        for seat_code in seat_text.readlines():
            # Removing new line char
            seat_code = seat_code[:-1]
            print(type(seat_code))

            seat_id = self._id_calculator(seat_code)
            self.seat_dict[seat_id] = seat_code

    def _id_calculator(self, bin_code):
        row_num = list(range(0,128))
        col_num = list(range(0,8))
        for letter in bin_code[:7]:
            row_num = self._row_selecter(letter, row_num)
            print(row_num, "row")
        for letter in bin_code[-3:]:
            col_num = self._col_selecter(letter, col_num)
            print(col_num, "col")
        id = row_num[0] * 8 + col_num[0]
        if id > self.max_id: self.max_id = id
        return id
        
    
    def _row_selecter(self, letter, seat_list):

        if      letter == 'F': return seat_list[:int(len(seat_list)/2)]
        elif    letter == 'B': return seat_list[int(len(seat_list)/2):]
        else: print("Something wrong in row selector")
    
    def _col_selecter(self, letter, seat_list):
        if letter == 'R': return seat_list[int(len(seat_list)/2):]
        elif letter == 'L': return seat_list[:int(len(seat_list)/2)]
        else: print("Galet i col selector")

    def print(self):
        print(self.seat_dict)

    def get_max(self):
        return self.max_id

if __name__ == '__main__':
    puzzle = Day5()
    puzzle.print()
    print(puzzle.get_max())

