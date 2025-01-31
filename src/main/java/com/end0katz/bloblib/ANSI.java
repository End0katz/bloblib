package com.end0katz.bloblib;

import com.end0katz.bloblib.io.Printer;

public class ANSI {

    protected char type = 'm';
    protected String params = "";

    static final String CSI = "\033[";

    public static void main(String[] args) {
        for (Graphics.PresetRGBs[] x : Graphics.PresetRGBs.BRIGHTSEQS) {
            for (Graphics.PresetRGBs i : x) {
                Graphics.FG.setcol(i).print();
                Main.print(i);
            }
        }
        Graphics.reset().print();
    }

    public ANSI() {
    }

    public ANSI print() {
        new Printer("", "").print(this);
        return this;
    }

    public static ANSI print(ANSI x) {
        return x.print();
    }

    public ANSI(String code) {
        this.type = code.charAt(code.length() - 1);
        this.params = code.substring(0, code.length() - 1);
    }

    @Override
    public String toString() {
        return this.toString(ANSI.CSI);
    }

    public String toString(String csi) {
        return csi + this.params + this.type;
    }

    public String toString(boolean csi) {
        return this.toString(csi ? ANSI.CSI : "");
    }

    public static class Graphics {

        public static ANSI setbold(boolean to) {
            return new ANSI(to ? "1" : "21");
        }

        public static ANSI setbold() {
            return setbold(true);
        }

        public static ANSI reset() {
            return new ANSI("m");
        }

        protected static class fgbg {

            protected int x;

            public fgbg(int X) {
                this.x = X;
            }

            public ANSI setcol(int r, int g, int b) {
                return new ANSI("%s;2;%s;%s;%sm".formatted(x + 8, r, g, b));
            }

            public ANSI setcol(int preset) {
                return new ANSI("38;5;%sm".formatted(preset));
            }

            public ANSI setcol(PresetCols x) {
                return this.setcol(x.toInt());
            }

            public ANSI setcol(PresetRGBs x) {
                return this.setcol(x.r(), x.g(), x.b());
            }

        }

        public enum PresetCols {
            BLACK(0),
            RED(1),
            GREEN(2),
            YELLOW(3),
            BLUE(4),
            MAGENTA(5),
            CYAN(6),
            WHITE(7),
            BRIGHT_BLACK(8),
            BRIGHT_RED(9),
            BRIGHT_GREEN(10),
            BRIGHT_YELLOW(11),
            BRIGHT_BLUE(12),
            BRIGHT_MAGENTA(13),
            BRIGHT_CYAN(14),
            BRIGHT_WHITE(15);

            protected int x;

            private PresetCols(int x) {
                this.x = x;
            }

            public int toInt() {
                return this.x;
            }
        }

        public enum PresetRGBs {
            RED("#ff0000"),
            ORANGE("#ffa500"),
            YELLOW("#ffff00"),
            GREEN("#00ff00"),
            CYAN("#00ffff"),
            BLUE("#0000ff"),
            PURPLE("#ff00ff"),
            GREY("#7f7f7f"),
            LRED("#ff2525"),
            LLRED("#ff4a4a"),
            LLLRED("#ff6f6f"),
            LLLLRED("#ff9494"),
            LLLLLRED("#ffb9b9"),
            DRED("#da0000"),
            DDRED("#b50000"),
            DDDRED("#900000"),
            DDDDRED("#6b0000"),
            DDDDDRED("#460000"),
            LORANGE("#ffca25"),
            LLORANGE("#ffef4a"),
            LLLORANGE("#ffff6f"),
            LLLLORANGE("#ffff94"),
            LLLLLORANGE("#ffffb9"),
            DORANGE("#da8000"),
            DDORANGE("#b55b00"),
            DDDORANGE("#903600"),
            DDDDORANGE("#6b1100"),
            DDDDDORANGE("#460000"),
            LYELLOW("#ffff25"),
            LLYELLOW("#ffff4a"),
            LLLYELLOW("#ffff6f"),
            LLLLYELLOW("#ffff94"),
            LLLLLYELLOW("#ffffb9"),
            DYELLOW("#dada00"),
            DDYELLOW("#b5b500"),
            DDDYELLOW("#909000"),
            DDDDYELLOW("#6b6b00"),
            DDDDDYELLOW("#464600"),
            LGREEN("#25ff25"),
            LLGREEN("#4aff4a"),
            LLLGREEN("#6fff6f"),
            LLLLGREEN("#94ff94"),
            LLLLLGREEN("#b9ffb9"),
            DGREEN("#00da00"),
            DDGREEN("#00b500"),
            DDDGREEN("#009000"),
            DDDDGREEN("#006b00"),
            DDDDDGREEN("#004600"),
            LCYAN("#25ffff"),
            LLCYAN("#4affff"),
            LLLCYAN("#6fffff"),
            LLLLCYAN("#94ffff"),
            LLLLLCYAN("#b9ffff"),
            DCYAN("#00dada"),
            DDCYAN("#00b5b5"),
            DDDCYAN("#009090"),
            DDDDCYAN("#006b6b"),
            DDDDDCYAN("#004646"),
            LBLUE("#2525ff"),
            LLBLUE("#4a4aff"),
            LLLBLUE("#6f6fff"),
            LLLLBLUE("#9494ff"),
            LLLLLBLUE("#b9b9ff"),
            DBLUE("#0000da"),
            DDBLUE("#0000b5"),
            DDDBLUE("#000090"),
            DDDDBLUE("#00006b"),
            DDDDDBLUE("#000046"),
            LPURPLE("#ff25ff"),
            LLPURPLE("#ff4aff"),
            LLLPURPLE("#ff6fff"),
            LLLLPURPLE("#ff94ff"),
            LLLLLPURPLE("#ffb9ff"),
            DPURPLE("#da00da"),
            DDPURPLE("#b500b5"),
            DDDPURPLE("#900090"),
            DDDDPURPLE("#6b006b"),
            DDDDDPURPLE("#460046"),
            LGREY("#a4a4a4"),
            LLGREY("#c9c9c9"),
            LLLGREY("#eeeeee"),
            LLLLGREY("#ffffff"),
            LLLLLGREY("#ffffff"),
            DGREY("#5a5a5a"),
            DDGREY("#353535"),
            DDDGREY("#101010"),
            DDDDGREY("#000000"),
            DDDDDGREY("#000000"),
            WHITE("#ffffff"),
            BLACK("#000000");

            protected int[] cols;

            //private PresetRGBs(int r, int g, int b) {
            //    this.cols = new int[]{r, g, b};
            //}
            private PresetRGBs(String hex) {
                this.cols = new int[]{
                    Integer.decode("0x" + hex.substring(1, 3)),
                    Integer.decode("0x" + hex.substring(3, 5)),
                    Integer.decode("0x" + hex.substring(5, 7))
                };
            }

            public static final PresetRGBs[] HUES = new PresetRGBs[]{RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE};
            public static final PresetRGBs[] GREYS = new PresetRGBs[]{
                PresetRGBs.DDDDDGREY,
                PresetRGBs.DDDDGREY,
                PresetRGBs.DDDGREY,
                PresetRGBs.DDGREY,
                PresetRGBs.DGREY,
                PresetRGBs.GREY,
                PresetRGBs.LGREY,
                PresetRGBs.LLGREY,
                PresetRGBs.LLLGREY,
                PresetRGBs.LLLLGREY,
                PresetRGBs.LLLLLGREY
            };

            public static final PresetRGBs[][] SEQS = new PresetRGBs[][]{
                new PresetRGBs[]{
                    RED.dark(5),
                    RED.dark(4),
                    RED.dark(3),
                    RED.dark(2),
                    RED.dark(1),
                    RED,
                    RED.bright(1),
                    RED.bright(2),
                    RED.bright(3),
                    RED.bright(4),
                    RED.bright(5)
                },
                new PresetRGBs[]{
                    ORANGE.dark(5),
                    ORANGE.dark(4),
                    ORANGE.dark(3),
                    ORANGE.dark(2),
                    ORANGE.dark(1),
                    ORANGE,
                    ORANGE.bright(1),
                    ORANGE.bright(2),
                    ORANGE.bright(3),
                    ORANGE.bright(4),
                    ORANGE.bright(5)
                },
                new PresetRGBs[]{
                    YELLOW.dark(5),
                    YELLOW.dark(4),
                    YELLOW.dark(3),
                    YELLOW.dark(2),
                    YELLOW.dark(1),
                    YELLOW,
                    YELLOW.bright(1),
                    YELLOW.bright(2),
                    YELLOW.bright(3),
                    YELLOW.bright(4),
                    YELLOW.bright(5)
                },
                new PresetRGBs[]{
                    GREEN.dark(5),
                    GREEN.dark(4),
                    GREEN.dark(3),
                    GREEN.dark(2),
                    GREEN.dark(1),
                    GREEN,
                    GREEN.bright(1),
                    GREEN.bright(2),
                    GREEN.bright(3),
                    GREEN.bright(4),
                    GREEN.bright(5)
                },
                new PresetRGBs[]{
                    CYAN.dark(5),
                    CYAN.dark(4),
                    CYAN.dark(3),
                    CYAN.dark(2),
                    CYAN.dark(1),
                    CYAN,
                    CYAN.bright(1),
                    CYAN.bright(2),
                    CYAN.bright(3),
                    CYAN.bright(4),
                    CYAN.bright(5)
                },
                new PresetRGBs[]{
                    BLUE.dark(5),
                    BLUE.dark(4),
                    BLUE.dark(3),
                    BLUE.dark(2),
                    BLUE.dark(1),
                    BLUE,
                    BLUE.bright(1),
                    BLUE.bright(2),
                    BLUE.bright(3),
                    BLUE.bright(4),
                    BLUE.bright(5)
                },
                new PresetRGBs[]{
                    PURPLE.dark(5),
                    PURPLE.dark(4),
                    PURPLE.dark(3),
                    PURPLE.dark(2),
                    PURPLE.dark(1),
                    PURPLE,
                    PURPLE.bright(1),
                    PURPLE.bright(2),
                    PURPLE.bright(3),
                    PURPLE.bright(4),
                    PURPLE.bright(5)
                }
            };

            public static final PresetRGBs[][] BRIGHTSEQS = new PresetRGBs[][]{
                new PresetRGBs[]{
                    DDDDDRED,
                    DDDDDORANGE,
                    DDDDDYELLOW,
                    DDDDDGREEN,
                    DDDDDCYAN,
                    DDDDDBLUE,
                    DDDDDPURPLE
                },
                new PresetRGBs[]{
                    DDDDRED,
                    DDDDORANGE,
                    DDDDYELLOW,
                    DDDDGREEN,
                    DDDDCYAN,
                    DDDDBLUE,
                    DDDDPURPLE
                },
                new PresetRGBs[]{
                    DDDRED,
                    DDDORANGE,
                    DDDYELLOW,
                    DDDGREEN,
                    DDDCYAN,
                    DDDBLUE,
                    DDDPURPLE
                },
                new PresetRGBs[]{
                    DDRED,
                    DDORANGE,
                    DDYELLOW,
                    DDGREEN,
                    DDCYAN,
                    DDBLUE,
                    DDPURPLE
                },
                new PresetRGBs[]{
                    DRED,
                    DORANGE,
                    DYELLOW,
                    DGREEN,
                    DCYAN,
                    DBLUE,
                    DPURPLE
                },
                new PresetRGBs[]{
                    RED,
                    ORANGE,
                    YELLOW,
                    GREEN,
                    CYAN,
                    BLUE,
                    PURPLE
                },
                new PresetRGBs[]{
                    LRED,
                    LORANGE,
                    LYELLOW,
                    LGREEN,
                    LCYAN,
                    LBLUE,
                    LPURPLE
                },
                new PresetRGBs[]{
                    LLRED,
                    LLORANGE,
                    LLYELLOW,
                    LLGREEN,
                    LLCYAN,
                    LLBLUE,
                    LLPURPLE
                },
                new PresetRGBs[]{
                    LLLRED,
                    LLLORANGE,
                    LLLYELLOW,
                    LLLGREEN,
                    LLLCYAN,
                    LLLBLUE,
                    LLLPURPLE
                },
                new PresetRGBs[]{
                    LLLLRED,
                    LLLLORANGE,
                    LLLLYELLOW,
                    LLLLGREEN,
                    LLLLCYAN,
                    LLLLBLUE,
                    LLLLPURPLE
                },
                new PresetRGBs[]{
                    LLLLLRED,
                    LLLLLORANGE,
                    LLLLLYELLOW,
                    LLLLLGREEN,
                    LLLLLCYAN,
                    LLLLLBLUE,
                    LLLLLPURPLE
                }
            };

            public int r() {
                return this.cols[0];
            }

            public int g() {
                return this.cols[1];
            }

            public int b() {
                return this.cols[2];
            }

            public PresetRGBs bright() {
                return switch (this) {
                    case RED ->
                        LRED;
                    case ORANGE ->
                        LORANGE;
                    case YELLOW ->
                        LYELLOW;
                    case GREEN ->
                        LGREEN;
                    case CYAN ->
                        LCYAN;
                    case BLUE ->
                        LBLUE;
                    case PURPLE ->
                        LPURPLE;
                    case GREY ->
                        LGREY;
                    case LRED ->
                        LLRED;
                    case LLRED ->
                        LLLRED;
                    case LLLRED ->
                        LLLLRED;
                    case LLLLRED ->
                        LLLLLRED;
                    case LLLLLRED ->
                        this;
                    case DRED ->
                        RED;
                    case DDRED ->
                        DRED;
                    case DDDRED ->
                        DDRED;
                    case DDDDRED ->
                        DDDRED;
                    case DDDDDRED ->
                        DDDDRED;
                    case LORANGE ->
                        LLORANGE;
                    case LLORANGE ->
                        LLLORANGE;
                    case LLLORANGE ->
                        LLLLORANGE;
                    case LLLLORANGE ->
                        LLLLLORANGE;
                    case LLLLLORANGE ->
                        this;
                    case DORANGE ->
                        ORANGE;
                    case DDORANGE ->
                        DORANGE;
                    case DDDORANGE ->
                        DDORANGE;
                    case DDDDORANGE ->
                        DDDORANGE;
                    case DDDDDORANGE ->
                        DDDDORANGE;
                    case LYELLOW ->
                        LLYELLOW;
                    case LLYELLOW ->
                        LLLYELLOW;
                    case LLLYELLOW ->
                        LLLLYELLOW;
                    case LLLLYELLOW ->
                        LLLLLYELLOW;
                    case LLLLLYELLOW ->
                        this;
                    case DYELLOW ->
                        YELLOW;
                    case DDYELLOW ->
                        DYELLOW;
                    case DDDYELLOW ->
                        DDYELLOW;
                    case DDDDYELLOW ->
                        DDDYELLOW;
                    case DDDDDYELLOW ->
                        DDDDYELLOW;
                    case LGREEN ->
                        LLGREEN;
                    case LLGREEN ->
                        LLLGREEN;
                    case LLLGREEN ->
                        LLLLGREEN;
                    case LLLLGREEN ->
                        LLLLLGREEN;
                    case LLLLLGREEN ->
                        this;
                    case DGREEN ->
                        GREEN;
                    case DDGREEN ->
                        DGREEN;
                    case DDDGREEN ->
                        DDGREEN;
                    case DDDDGREEN ->
                        DDDGREEN;
                    case DDDDDGREEN ->
                        DDDDGREEN;
                    case LCYAN ->
                        LLCYAN;
                    case LLCYAN ->
                        LLLCYAN;
                    case LLLCYAN ->
                        LLLLCYAN;
                    case LLLLCYAN ->
                        LLLLLCYAN;
                    case LLLLLCYAN ->
                        this;
                    case DCYAN ->
                        CYAN;
                    case DDCYAN ->
                        DCYAN;
                    case DDDCYAN ->
                        DDCYAN;
                    case DDDDCYAN ->
                        DDDCYAN;
                    case DDDDDCYAN ->
                        DDDDCYAN;
                    case LBLUE ->
                        LLBLUE;
                    case LLBLUE ->
                        LLLBLUE;
                    case LLLBLUE ->
                        LLLLBLUE;
                    case LLLLBLUE ->
                        LLLLLBLUE;
                    case LLLLLBLUE ->
                        this;
                    case DBLUE ->
                        BLUE;
                    case DDBLUE ->
                        DBLUE;
                    case DDDBLUE ->
                        DDBLUE;
                    case DDDDBLUE ->
                        DDDBLUE;
                    case DDDDDBLUE ->
                        DDDDBLUE;
                    case LPURPLE ->
                        LLPURPLE;
                    case LLPURPLE ->
                        LLLPURPLE;
                    case LLLPURPLE ->
                        LLLLPURPLE;
                    case LLLLPURPLE ->
                        LLLLLPURPLE;
                    case LLLLLPURPLE ->
                        this;
                    case DPURPLE ->
                        PURPLE;
                    case DDPURPLE ->
                        DPURPLE;
                    case DDDPURPLE ->
                        DDPURPLE;
                    case DDDDPURPLE ->
                        DDDPURPLE;
                    case DDDDDPURPLE ->
                        DDDDPURPLE;
                    case LGREY ->
                        LLGREY;
                    case LLGREY ->
                        LLLGREY;
                    case LLLGREY ->
                        LLLLGREY;
                    case LLLLGREY ->
                        LLLLLGREY;
                    case LLLLLGREY ->
                        this;
                    case DGREY ->
                        GREY;
                    case DDGREY ->
                        DGREY;
                    case DDDGREY ->
                        DDGREY;
                    case DDDDGREY ->
                        DDDGREY;
                    case DDDDDGREY ->
                        DDDDGREY;
                    case WHITE ->
                        this;
                    case BLACK ->
                        this;

                };

            }

            public PresetRGBs dark() {
                return switch (this) {
                    case RED ->
                        DRED;
                    case ORANGE ->
                        DORANGE;
                    case YELLOW ->
                        DYELLOW;
                    case GREEN ->
                        DGREEN;
                    case CYAN ->
                        DCYAN;
                    case BLUE ->
                        DBLUE;
                    case PURPLE ->
                        DPURPLE;
                    case GREY ->
                        DGREY;
                    case LRED ->
                        RED;
                    case LLRED ->
                        LRED;
                    case LLLRED ->
                        LLRED;
                    case LLLLRED ->
                        LLLRED;
                    case LLLLLRED ->
                        LLLLRED;
                    case DRED ->
                        DDRED;
                    case DDRED ->
                        DDDRED;
                    case DDDRED ->
                        DDDDRED;
                    case DDDDRED ->
                        DDDDDRED;
                    case DDDDDRED ->
                        this;
                    case LORANGE ->
                        ORANGE;
                    case LLORANGE ->
                        LORANGE;
                    case LLLORANGE ->
                        LLORANGE;
                    case LLLLORANGE ->
                        LLLORANGE;
                    case LLLLLORANGE ->
                        LLLLORANGE;
                    case DORANGE ->
                        DDORANGE;
                    case DDORANGE ->
                        DDDORANGE;
                    case DDDORANGE ->
                        DDDDORANGE;
                    case DDDDORANGE ->
                        DDDDDORANGE;
                    case DDDDDORANGE ->
                        this;
                    case LYELLOW ->
                        YELLOW;
                    case LLYELLOW ->
                        LYELLOW;
                    case LLLYELLOW ->
                        LLYELLOW;
                    case LLLLYELLOW ->
                        LLLYELLOW;
                    case LLLLLYELLOW ->
                        LLLLYELLOW;
                    case DYELLOW ->
                        DDYELLOW;
                    case DDYELLOW ->
                        DDDYELLOW;
                    case DDDYELLOW ->
                        DDDDYELLOW;
                    case DDDDYELLOW ->
                        DDDDDYELLOW;
                    case DDDDDYELLOW ->
                        this;
                    case LGREEN ->
                        GREEN;
                    case LLGREEN ->
                        LGREEN;
                    case LLLGREEN ->
                        LLGREEN;
                    case LLLLGREEN ->
                        LLLGREEN;
                    case LLLLLGREEN ->
                        LLLLGREEN;
                    case DGREEN ->
                        DDGREEN;
                    case DDGREEN ->
                        DDDGREEN;
                    case DDDGREEN ->
                        DDDDGREEN;
                    case DDDDGREEN ->
                        DDDDDGREEN;
                    case DDDDDGREEN ->
                        this;
                    case LCYAN ->
                        CYAN;
                    case LLCYAN ->
                        LCYAN;
                    case LLLCYAN ->
                        LLCYAN;
                    case LLLLCYAN ->
                        LLLCYAN;
                    case LLLLLCYAN ->
                        LLLLCYAN;
                    case DCYAN ->
                        DDCYAN;
                    case DDCYAN ->
                        DDDCYAN;
                    case DDDCYAN ->
                        DDDDCYAN;
                    case DDDDCYAN ->
                        DDDDDCYAN;
                    case DDDDDCYAN ->
                        this;
                    case LBLUE ->
                        BLUE;
                    case LLBLUE ->
                        LBLUE;
                    case LLLBLUE ->
                        LLBLUE;
                    case LLLLBLUE ->
                        LLLBLUE;
                    case LLLLLBLUE ->
                        LLLLBLUE;
                    case DBLUE ->
                        DDBLUE;
                    case DDBLUE ->
                        DDDBLUE;
                    case DDDBLUE ->
                        DDDDBLUE;
                    case DDDDBLUE ->
                        DDDDDBLUE;
                    case DDDDDBLUE ->
                        this;
                    case LPURPLE ->
                        PURPLE;
                    case LLPURPLE ->
                        LPURPLE;
                    case LLLPURPLE ->
                        LLPURPLE;
                    case LLLLPURPLE ->
                        LLLPURPLE;
                    case LLLLLPURPLE ->
                        LLLLPURPLE;
                    case DPURPLE ->
                        DDPURPLE;
                    case DDPURPLE ->
                        DDDPURPLE;
                    case DDDPURPLE ->
                        DDDDPURPLE;
                    case DDDDPURPLE ->
                        DDDDDPURPLE;
                    case DDDDDPURPLE ->
                        this;
                    case LGREY ->
                        GREY;
                    case LLGREY ->
                        LGREY;
                    case LLLGREY ->
                        LLGREY;
                    case LLLLGREY ->
                        LLLGREY;
                    case LLLLLGREY ->
                        LLLLGREY;
                    case DGREY ->
                        DDGREY;
                    case DDGREY ->
                        DDDGREY;
                    case DDDGREY ->
                        DDDDGREY;
                    case DDDDGREY ->
                        DDDDDGREY;
                    case DDDDDGREY ->
                        this;
                    case WHITE ->
                        this;
                    case BLACK ->
                        this;

                };

            }

            public PresetRGBs dark(int x) {
                PresetRGBs result = this;
                for (int i = x; i > 0; i--) {
                    result = result.dark();
                }
                return result;
            }

            public PresetRGBs bright(int x) {
                PresetRGBs result = this;
                for (int i = x; i > 0; i--) {
                    result = result.bright();
                }
                return result;
            }
        }

        public static final fgbg FG = new fgbg(30);
        public static final fgbg BG = new fgbg(40);
    }
}
