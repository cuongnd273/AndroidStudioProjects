#include "com_example_cuong_demoopencv_OpenCVClass.h"
JNIEXPORT void JNICALL Java_com_example_cuong_demoopencv_OpenCVClass_faceDetection
  (JNIEnv *, jclass, jlong addrRgba){
    Mat& frame=*(Mat*)addrRgba;
    detect(frame);
}
void detect(Mat& frame){

}

