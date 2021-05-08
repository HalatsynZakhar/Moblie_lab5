package ua.kpi.comsys.io8207;

import androidx.annotation.NonNull;

import static java.lang.Math.round;

public class Lab1_2_CoordinateZH extends Lab1_2_Direction {

    Lab1_2_CoordinateZH(){
        this.gradus=0;
        this.minuta=0;
        this.secunda=0;
    }

    @NonNull
    @Override
    public String toString() {
        return outCoordinate();
    }
    Lab1_2_CoordinateZH(int gradus, int minuta, int secunda, boolean dolgotqZeroShurotaone){
        if(dolgotqZeroShurotaone){
            if ((gradus >= -90) && (gradus <= 90)) {
                this.gradus = gradus;
            }
        }else{
           if ((gradus >= -180) && (gradus <= 180)) {
                this.gradus = gradus;
            }
        }
        boolean notife = false;
        if(((minuta >= 0) && (minuta <= 59)) && !((gradus == 180 || gradus == -180) && !dolgotqZeroShurotaone && minuta > 0) && !((gradus == 90 || gradus == -90) && dolgotqZeroShurotaone && minuta > 0)){
            this.minuta = minuta;
        }else{
            notife = true;
        }
        if(((secunda >= 0) && (secunda <= 59))&& !((gradus == 180 || gradus == -180) && !dolgotqZeroShurotaone && secunda > 0 ) && !((gradus == 90 || gradus == -90) && dolgotqZeroShurotaone && secunda > 0)) {
            this.secunda = secunda;
        }else {
            notife = true;
        };
        this.dolgotqZeroShurotaone = dolgotqZeroShurotaone;

        if (notife) {
            System.out.println("Некоректні координати, перевищують максимальну. Встановлюється максимальне можливе значення");
        }
    }

    String outCoordinate(){
        String outText = "";
        if (this.dolgotqZeroShurotaone){
            if(this.gradus > 0){
                outText = "N";
            }
            else if(this.gradus <0){
                outText = "S";
            }
        } else {
            if(this.gradus > 0) {
                outText = "E";
            } else if (this.gradus < 0) {
                outText = "W";
            }
        }
        return Math.abs(this.gradus) + "° " + this.minuta + "' " + this.secunda + "'' " + outText;
    }

    String outCoordinate2() {
        String outText = "";
        if (this.dolgotqZeroShurotaone) {
            if (this.gradus > 0) {
                outText = "N";
            } else if (this.gradus < 0) {
                outText = "S";
            }
        } else {
            if (this.gradus > 0) {
                outText = "E";
            } else if (this.gradus < 0) {
                outText = "W";
            }
        }
        return Math.abs(this.gradus) + "." + round( (this.minuta*60 + this.secunda)/3.6*10000) + outText;
    }

    Lab1_2_CoordinateZH outCoordinate3(int x, int y, int z, boolean shurota) {
        if (shurota != this.dolgotqZeroShurotaone) {
            return null;
        }

        String outText = "";
        if (this.dolgotqZeroShurotaone) {
            if ((this.gradus + x) / 2 > 0) {
                outText = "N";
            } else if ((this.gradus + x) / 2 < 0) {
                outText = "S";
            }
        } else {
            if ((this.gradus + x) / 2 > 0) {
                outText = "E";
            } else if ((this.gradus + x) / 2 < 0) {
                outText = "W";
            }
        }
        if ((this.gradus >= 0 && x >= 0) || (this.gradus <= 0 && x <= 0)) {
            return new Lab1_2_CoordinateZH(Math.abs((this.gradus + x) / 2), (this.minuta + y) / 2, (this.secunda + z) / 2, shurota);
        } else {
            return new Lab1_2_CoordinateZH(Math.abs((this.gradus + x) / 2), Math.abs(this.minuta - y), Math.abs(this.secunda - z), shurota);
        }
    }


    public static Lab1_2_CoordinateZH outCoordinate4(int x1, int y1, int z1, boolean shurota1, int x2, int y2, int z2, boolean shurota2) {
        if (shurota1 != shurota2){
            return null;
        }
        String outText = "";
        if (shurota1) {
            if ((x1 + x2) / 2 > 0) {
                outText = "N";
            } else if ((x1 + x2) / 2 < 0) {
                outText = "S";
            }
        } else {
            if ((x1 + x2) / 2 > 0) {
                outText = "E";
            } else if ((x1 + x2) / 2 < 0) {
                outText = "W";
            }
        }
        if ((x1 >= 0 && x2 >= 0) || (x1 <=0 && x2 <=0)) {
            Lab1_2_CoordinateZH ret = new Lab1_2_CoordinateZH(Math.abs((x1 + x2) / 2), (y1 + y2) / 2, (z1 + z2) / 2, shurota1);
            return ret;
        } else {
            Lab1_2_CoordinateZH ret = new Lab1_2_CoordinateZH(Math.abs((x1 + x2) / 2), Math.abs(y1 - y2), Math.abs(z1-z2), shurota1);
            return ret;
        }
    }

}

