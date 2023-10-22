
<template>
    <div style="background-color: #fff;padding: 30px;border-radius: 30px;font-size: 26px;font-weight: 700;">
        <div style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
            Share</div>
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
                        <!--                        <source src="../../../流浪猫/video/1.mp4" type="audio/ogg">
                        <source src="../../../流浪猫/video/1.mp4" type="audio/mpeg">-->
                        您的浏览器不支持 audio 元素。
                    </audio>
                </div>
            </div>
            <div>
                <div
                    style="border-bottom: 5px solid orangered;display: inline-block;padding-bottom: 10px;margin-bottom: 20PX;">
                    Memory Video</div>
                <div style="width: 260px;height: 240px;">
                    <video style="width: 100%;height: 100%;" controls>
                        <!--                        <source src="../../../流浪猫/video/1.mp4" type="video/mp4">
                        <source src="../../../流浪猫/video/2.mp4" type="video/ogg">-->
                        您的浏览器不支持Video标签。
                    </video>
                </div>
            </div>
        </div>
        <div style="margin-top: 30px;">
        </div>
    </div>
</template>

<script lang="ts">

export default {
    setup() {

        return {

        }
    }
}
</script>


<style></style>