"use strict";(self["webpackChunkaurora_blog"]=self["webpackChunkaurora_blog"]||[]).push([[166],{28303:function(e,t,r){r.r(t),r.d(t,{default:function(){return ue}});var a=r(73396),i=r(87139);const l=e=>((0,a.dD)("data-v-2487c3bb"),e=e(),(0,a.Cn)(),e),o={class:"flex flex-col"},n={class:"main-grid"},s={class:"post-header"},c={class:"post-labels"},d={key:1},m={key:0,class:"post-title text-white"},p={class:"flex flex-row items-center justify-start mt-8 mb-4"},h={key:0,class:"post-footer"},u={class:"text-white opacity-80"},g={class:"opacity-70"},y={key:2,class:"post-stats"},w={class:"pl-2"},x={class:"pl-2"},v={class:"main-grid",style:{width:"100%","background-color":"#fff","border-radius":"30px",padding:"30px"}},f=(0,a.Uk)("Share"),b={style:{display:"flex"}},C=l((()=>(0,a._)("div",{style:{"border-bottom":"5px solid orangered",display:"inline-block","padding-bottom":"10px","margin-bottom":"20PX","font-size":"22px"}}," Memory lmage",-1))),_={style:{width:"260px",height:"240px"}},k=["src"],A={style:{margin:"0 260px"}},T=l((()=>(0,a._)("div",{style:{"border-bottom":"5px solid orangered",display:"inline-block","padding-bottom":"10px","margin-bottom":"20PX","font-size":"22px"}}," Memory Audio",-1))),D={style:{width:"260px",height:"240px"}},M={controls:""},z=["src"],I=l((()=>(0,a._)("div",{style:{"border-bottom":"5px solid orangered",display:"inline-block","padding-bottom":"10px","margin-bottom":"20PX","font-size":"22px"}}," Memory Video",-1))),Z={style:{width:"260px",height:"240px"}},W={style:{width:"100%",height:"100%"},controls:""},j=["src"],R={class:"post-html",style:{width:"100%"}},S=l((()=>(0,a._)("div",{style:{"font-size":"22px",border:"10px","font-weight":"700"}},"Description:",-1))),H=["innerHTML"],N={key:1,class:"bg-ob-deep-800 px-14 py-16 rounded-2xl shadow-xl block min-h-screen"},P=l((()=>(0,a._)("br",null,null,-1))),F=l((()=>(0,a._)("br",null,null,-1))),q=l((()=>(0,a._)("br",null,null,-1))),B={class:"flex flex-col lg:flex-row justify-start items-end my-8 my-gap"},E={key:0,class:"w-full h-full self-stretch mr-0 lg:mr-4"},J={key:1,class:"w-full h-full self-stretch mt-0"},L=l((()=>(0,a._)("div",null,null,-1)));function Y(e,t,r,l,Y,O){const U=(0,a.up)("ob-skeleton"),X=(0,a.up)("svg-icon"),$=(0,a.up)("el-button"),K=(0,a.up)("SubTitle"),Q=(0,a.up)("ArticleCard"),V=(0,a.Q2)("lazy");return(0,a.wg)(),(0,a.iD)("div",o,[(0,a._)("div",n,[(0,a._)("div",s,[(0,a._)("span",c,[e.loading?((0,a.wg)(),(0,a.j4)(U,{key:0,tag:"b",height:"20px",width:"35px"})):((0,a.wg)(),(0,a.iD)("b",d,(0,i.zw)(e.t("settings.default-category")),1)),(0,a._)("ul",null,[e.loading?((0,a.wg)(),(0,a.j4)(U,{key:0,count:2,tag:"li",height:"16px",width:"35px",class:"mr-2"})):(0,a.kq)("",!0)])]),e.article.memoryTitle?((0,a.wg)(),(0,a.iD)("h1",m,(0,i.zw)(e.article.memoryTitle),1)):((0,a.wg)(),(0,a.j4)(U,{key:1,class:"post-title text-white uppercase",width:"100%",height:"clamp(1.2rem, calc(1rem + 3.5vw), 4rem)"})),(0,a._)("div",p,[e.article.author?((0,a.wg)(),(0,a.iD)("div",h,[(0,a.wy)((0,a._)("img",{class:"hover:opacity-50 cursor-pointer",alt:"author avatar",onClick:t[0]||(t[0]=t=>e.handleAuthorClick(e.article.author.website))},null,512),[[V,e.article.author.avatar||""]]),(0,a._)("span",u,[(0,a._)("strong",{class:"text-white pr-1.5 hover:opacity-50 cursor-pointer",onClick:t[1]||(t[1]=t=>e.handleAuthorClick(e.article.author.website))},(0,i.zw)(e.article.author.nickname),1),(0,a._)("span",g,(0,i.zw)(e.t("settings.shared-on"))+" "+(0,i.zw)(e.t(`settings.months[${new Date(e.article.createTime).getMonth()}]`))+" "+(0,i.zw)(new Date(e.article.createTime).getDate())+", "+(0,i.zw)(new Date(e.article.createTime).getFullYear()),1)])])):((0,a.wg)(),(0,a.iD)("div",y,[(0,a._)("span",null,[(0,a.Wm)(X,{"icon-class":"clock"}),(0,a._)("span",w,[(0,a.Wm)(U,{width:"40px",height:"16px"})])]),(0,a._)("span",null,[(0,a.Wm)(X,{"icon-class":"text"}),(0,a._)("span",x,[(0,a.Wm)(U,{width:"40px",height:"16px"})])])]))])])]),(0,a._)("div",v,[(0,a._)("div",null,[(0,a.Wm)($,{style:{position:"absolute",right:"60px",top:"30px",width:"90px"},onClick:e.share,type:"primary"},{default:(0,a.w5)((()=>[f])),_:1},8,["onClick"]),e.article.memoryContent?((0,a.wg)(),(0,a.iD)(a.HY,{key:0},[(0,a._)("div",b,[(0,a._)("div",null,[C,(0,a._)("div",_,[(0,a._)("img",{style:{width:"100%"},src:e.article.memoryCover,alt:""},null,8,k)])]),(0,a._)("div",A,[T,(0,a._)("div",D,[(0,a._)("audio",M,[(0,a._)("source",{src:e.article.audioUrl,type:"audio/ogg"},null,8,z)])])]),(0,a._)("div",null,[I,(0,a._)("div",Z,[(0,a._)("video",W,[(0,a._)("source",{src:e.article.videoUrl,type:"video/mp4"},null,8,j)])])])]),(0,a._)("div",R,[S,(0,a._)("div",{class:"markdown-body",ref:"articleRef",innerHTML:e.article.memoryContent},null,8,H)])],64)):((0,a.wg)(),(0,a.iD)("div",N,[(0,a.Wm)(U,{tag:"div",count:1,height:"36px",width:"150px",class:"mb-6"}),P,(0,a.Wm)(U,{tag:"div",count:35,height:"16px",width:"100px",class:"mr-2"}),F,q,(0,a.Wm)(U,{tag:"div",count:25,height:"16px",width:"100px",class:"mr-2"})])),(0,a._)("div",B,[e.preArticleCard?((0,a.wg)(),(0,a.iD)("div",E,[(0,a.Wm)(K,{title:"settings.paginator.pre",icon:"arrow-left-circle"}),(0,a.Wm)(Q,{class:"pre-and-next-article",data:e.preArticleCard},null,8,["data"])])):(0,a.kq)("",!0),e.nextArticleCard?((0,a.wg)(),(0,a.iD)("div",J,[(0,a.Wm)(Q,{class:"pre-and-next-article",data:e.nextArticleCard},null,8,["data"])])):(0,a.kq)("",!0)])]),L])])}var O=r(4922),U=r(44870),X=r(22483),$=r(35213),K=r(23925),Q=r(50913),V=r(69298),G=r(45466),ee=r(17437),te=r(62069),re=r(2549),ae=r.n(re),ie=(r(79525),r(33731),r(77372),r(61678),r(3275),r(28781),r(51973),r(61739),r(70714),r(8072),r(9688),r(64121),r(80077),r(23456),r(43774)),le=r.n(ie),oe=r(13184),ne=r(36883),se=r(73286),ce=r(69622),de=r(96e3),me=(0,a.aZ)({name:"Article",components:{Sidebar:O.YE,Comment:K.s,SubTitle:Q.D,ArticleCard:V.T,Profile:O.NZ,Sticky:te.Z,Navigator:O.C3},setup(){const e=(0,a.FN)()?.appContext.config.globalProperties,t=(0,G.E)(),r=(0,ee.j)(),i=(0,X.yj)(),l=(0,X.tv)(),{t:o}=(0,$.QT)(),n=(0,U.iH)(!0),s=(0,U.iH)(),c=(0,U.qj)({articleId:"",article:"",wordNum:"",readTime:"",comments:[],images:[],preArticleCard:"",nextArticleCard:"",haveMore:!1,isReload:!1}),d=(0,U.qj)({current:1,size:7});r.type=1,(0,a.bv)((()=>{c.articleId=i.params.articleId,w(),h(),u()})),(0,a.Ah)((()=>{t.resetHeaderImage(),c.article="",le().destroy()})),(0,X.ao)((e=>{c.article="",c.readTime="",c.wordNum="",c.comments=[],c.images=[],c.preArticleCard="",c.nextArticleCard="",c.articleId=e.params.articleId,d.current=1,c.isReload=!0,w(),h(),u()})),(0,a.JJ)("comments",(0,a.Fl)((()=>c.comments))),(0,a.JJ)("haveMore",(0,a.Fl)((()=>c.haveMore))),oe.Z.on("articleFetchComment",(()=>{d.current=1,c.isReload=!0,u()})),oe.Z.on("articleFetchReplies",(e=>{g(e)})),oe.Z.on("articleLoadMore",(()=>{u()}));const m=e=>{(0,ne.e)({images:c.images,index:c.images.indexOf(e)})},p=()=>{let e=s.value.children;if(e.length)for(let a=0;a<e.length;a++){let t=e[a],r=/^H[1-4]{1}$/;r.exec(t.tagName)&&(t.id=a)}le().init({tocSelector:"#toc1",contentSelector:".post-html",headingSelector:"h1, h2, h3",onClick:function(e){e.preventDefault()}});const t=s.value.getElementsByTagName("img");for(var r=0;r<t.length;r++)c.images.push(t[r].src),t[r].addEventListener("click",(function(e){m(e.target.currentSrc)}))},h=()=>{n.value=!0,se.Z.getArticeById(c.articleId).then((({data:r})=>{if(52003===r.code)return e.$notify({title:"Error",message:"文章密码认证未通过",type:"error"}),void l.push({path:"/出错啦"});null!==r.data?(t.setHeaderImage(r.data.memoryCover),new Promise((e=>{r.data.memoryContent=(0,ce.Z)(r.data.memoryContent),e(r.data)})).then((e=>{c.article=e,c.wordNum=Math.round(x(e.memoryContent).length/100)/10+"k",c.readTime=Math.round(x(e.memoryContent).length/400)+"mins",n.value=!1,(0,a.Y3)((()=>{ae().highlightAll(),p()}))})),new Promise((e=>{r.data.preArticleCard.memoryContent=(0,ce.Z)(r.data.preArticleCard.memoryContent).replace(/<\/?[^>]*>/g,"").replace(/[|]*\n/,"").replace(/&npsp;/gi,""),e(r.data.preArticleCard)})).then((e=>{c.preArticleCard=e})),new Promise((e=>{r.data.nextArticleCard.memoryContent=(0,ce.Z)(r.data.nextArticleCard.memoryContent).replace(/<\/?[^>]*>/g,"").replace(/[|]*\n/,"").replace(/&npsp;/gi,""),e(r.data.nextArticleCard)})).then((e=>{c.nextArticleCard=e}))):l.push({path:"/出错啦"})}))},u=()=>{const e={type:1,topicId:c.articleId,current:d.current,size:d.size};se.Z.getComments(e).then((({data:e})=>{c.isReload?(c.comments=e.data.records,c.isReload=!1):c.comments.push(...e.data.records),e.data.count<=c.comments.length?c.haveMore=!1:c.haveMore=!0,d.current++}))},g=e=>{se.Z.getRepliesByCommentId(c.comments[e].id).then((({data:t})=>{c.comments[e].replyDTOs=t.data}))},y=e=>{""===e&&(e=window.location.href),window.location.href=e},w=()=>{window.scrollTo({top:0})},x=e=>e.replace(/<\/?[^>]*>/g,"").replace(/[|]*\n/,"").replace(/&npsp;/gi,""),v=()=>{de.T.alert(window.location.href,"share",{confirmButtonText:"OK"})};return{articleRef:s,...(0,U.BK)(c),isMobile:(0,a.Fl)((()=>t.isMobile)),handleAuthorClick:y,loading:n,t:o,share:v}}}),pe=r(40089);const he=(0,pe.Z)(me,[["render",Y],["__scopeId","data-v-2487c3bb"]]);var ue=he}}]);