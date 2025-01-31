package com.end0katz.bloblib;

public class BlobjectTest {

    static class BlobRecord extends Blobject<BlobRecord> implements Blobject.Readable {

        public int ValueToStore;

        // public Blobject.Data<BlobRecord> blobject_data = new Blobject.Data.Builder<>(new BlobRecord(0))
        //         .newinstance(() -> new BlobRecord(0)).build();
        public BlobRecord(int ValueToStore) {
            this.ValueToStore = ValueToStore;
        }

        @Override
        public String toString() {
            return Integer.toString(ValueToStore);
        }

    }

    public static void main(String[] args) {
        BlobRecord x = new BlobRecord(5);
        System.out.println(x);
        System.out.println(x.clone());
        x.read(null);
    }
}
