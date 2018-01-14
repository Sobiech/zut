% z podobienstwa ~ trojkatow a/b = a1/b1 dla alfa = alfa1
function result = heightT(y, yMax, yMin, zMax, zMin)
    
    newY = yMax - y;
    
    newZ = (zMax - zMin)/(yMax - yMin) * newY;
    result = zMax - newZ;