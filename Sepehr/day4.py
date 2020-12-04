import re
class Day4:
    def __init__(self):
        self.passport_data = self._format_data()

    def _format_data(self):
        input = open('inputs/input4.txt', 'r')
        pass_dict = dict()
        pass_id = 0
        pass_string = ''
        for i, line in enumerate(input.readlines()):
            if line.startswith('\n'):
                #print(i, line, "startar tydligen med det")
                # Här kastar vi in pass string i en formaterare. Så att
                # den returneras i key:value på snyggt sätt
                pass_dict[pass_id] = self._pass_formater(pass_string.replace('\n', ' '))
                pass_id += 1
                pass_string = ''
            else: 
                pass_string += line
        return pass_dict
            
    def _pass_formater(self, pass_string):
        ''' Input a string in type key:value key2:value2 ...'''
        res_dict = dict()
        for i, string in enumerate(pass_string.split(' ')[:-1]):
            key_values = string.split(':')
            res_dict[key_values[0]] = key_values[1]
        # Detta funkar tydligen ikke
        #res_dict['valid'] = ('byr' , 'iyr' , 'eyr' , 'hgt' , 'hcl' , 'ecl' , 'pid') in res_dict
        res_dict['valid'] = self._valid_checker(res_dict)

        return res_dict

    def _valid_checker(self, pass_dict):


        # Not needed, but will cancel checks before if not all fields exist
        if not self._valid_fields(pass_dict): return False

        res = (self._valid_fields(pass_dict) and 
                self._valid_byr(pass_dict) and
                self._valid_iyr(pass_dict) and 
                self._valid_eyr(pass_dict) and 
                self._valid_hgt(pass_dict) and
                self._valid_hcl(pass_dict) and 
                self._valid_ecl(pass_dict) and
                self._valid_pid(pass_dict))
        return res


    def _valid_fields(self, pass_dict):
        
        if all (keys in pass_dict for keys in ('byr' , 'iyr' , 'eyr' , 'hgt' , 'hcl' , 'ecl' , 'pid')):
            return True
        return False
    
    def _valid_byr(self, pass_dict):
        return 1920 <= int(pass_dict['byr']) <= 2002

    def _valid_iyr(self, pass_dict):
        return 2010 <= int(pass_dict['iyr']) <= 2020

    def _valid_eyr(self, pass_dict):
        return 2020 <= int(pass_dict['eyr']) <= 2030

    def _valid_hgt(self, pass_dict):
        unit = pass_dict['hgt'][-2:]
        if unit == 'cm':
            return 150 <= int(pass_dict['hgt'][:-2]) <= 193
        elif unit == 'in':
            return 59 <= int(pass_dict['hgt'][:-2]) <= 76

    def _valid_hcl(self, pass_dict):
        return re.search('^#[a-fA-F0-9]{6}',pass_dict['hcl'])

    def _valid_ecl(self, pass_dict):
        return pass_dict['ecl'] in ['amb' , 'blu' , 'brn' ,  'gry' , 'grn' , 'hzl' , 'oth']

    def _valid_pid(self, pass_dict):
        reg = re.search('^[0-9]{9}', pass_dict['pid'])
        return reg and len(pass_dict['pid'])==9

    def count_valid(self):
        count = 0 
        count_bad = 0
        for key in self.passport_data:
            if self.passport_data[key]['valid']:
                count+= 1
        return count

    def print(self):
        nyckel = 'ecl'
        for key in self.passport_data:
            if (self.passport_data[key]['valid']):
                print(self.passport_data[key][nyckel])

if __name__ == '__main__':
    puzzle4 = Day4()

    print(puzzle4.count_valid(), "svar")
    #puzzle4.print()
