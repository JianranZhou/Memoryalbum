
<template>
    <div style="background-color: #fff;padding: 30px;border-radius: 30px;font-size: 26px;font-weight: 700;">
        <!-- 分享按钮 -->
        <el-button style="position: absolute;right: 60px;top: 30px;width: 90px;" @click="share"
            type="primary">Share</el-button>
        <div style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
            Memory Title</div>
        <div style="display: flex;">
            <div style="margin-right: 220px;">location: China</div>
            <div>Tags: tag1、tag2、Tag3</div>
        </div>
        <div style="display: flex;margin-top: 80px;">
            <div>
                <div
                    style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
                    Memory lmage</div>
                <div style="width: 260px;height: 240px;"><img style="width: 100%;" src="../assets/default-cover.jpg" alt="">
                </div>
            </div>
            <div style="margin: 0 260px;">
                <div
                    style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
                    Memory Audio</div>
                <div style="width: 260px;height: 240px;">
                    <audio controls>
                    </audio>
                </div>
            </div>
            <div>
                <div
                    style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
                    Memory Video</div>
                <div style="width: 260px;height: 240px;">
                    <video style="width: 100%;height: 100%;" controls>
                    </video>
                </div>
            </div>
        </div>
        <div style="margin-top: 30px;">
        </div>
    </div>
</template>

<script lang="ts">
import { ElMessage } from 'element-plus'
import { ElMessageBox } from 'element-plus'
export default {
    setup() {

        const share = () => {
            ElMessageBox.alert('http://localhost:8080/share', 'share', {
                confirmButtonText: 'OK',
            })
        }
        return {
            share
        }
    }
}
</script>


<style></style>