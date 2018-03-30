class Util():

    def utc_to_br(utc_hms):
        utc_h = int(utc_hms[:-4])
        br_h = ""
        if utc_h - 3 <= 0:
            if utc_h == 0:
                br_h = '21'
            elif utc_h == 1:
                br_h = '22'
            elif utc_h == 2:
                br_h = '23'
        else:
            if utc_h <= 12 and utc_h > 2:
                  br_h = '0'
                  br_h += str(utc_h - 3)
            else:
                  br_h = str(utc_h - 3)
        return br_h+utc_hms[2:]
