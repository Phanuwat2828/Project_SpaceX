
Panumart Saithanu
private void resolveCollision(MoveMeteorito meteor1, MoveMeteorito meteor2) {
        // ปรับตำแหน่งของอุกกาบาตเมื่อชนกัน
        Rectangle hitBox1 = meteor1.getHitBox();
        Rectangle hitBox2 = meteor2.getHitBox();

        int overlapX = Math.min(hitBox1.x + hitBox1.width, hitBox2.x + hitBox2.width) - Math.max(hitBox1.x, hitBox2.x);
        int overlapY = Math.min(hitBox1.y + hitBox1.height, hitBox2.y + hitBox2.height) - Math.max(hitBox1.y, hitBox2.y);

        if (overlapX < overlapY) {
            if (hitBox1.x < hitBox2.x) {
                meteor1.setX(meteor1.getX() - overlapX); // เลื่อน meteor1 ไปทางซ้าย
            } else {
                meteor1.setX(meteor1.getX() + overlapX); // เลื่อน meteor1 ไปทางขวา
            }
        } else {
            if (hitBox1.y < hitBox2.y) {
                meteor1.setY(meteor1.getY() - overlapY); // เลื่อน meteor1 ขึ้น
            } else {
                meteor1.setY(meteor1.getY() + overlapY); // เลื่อน meteor1 ลง
            }
        }

        // เปลี่ยนทิศทาง
        meteor1.handleCollision();
        meteor2.handleCollision();
    }
}