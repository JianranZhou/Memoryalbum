"use strict";(self["webpackChunkaurora_blog"]=self["webpackChunkaurora_blog"]||[]).push([[229],{47919:function(e,t,n){n.d(t,{Z:function(){return d}});var a=n(73396),r=n(87139);const o={class:"breadcrumbs flex flex-row gap-6 text-white"};function s(e,t,n,s,l,c){return(0,a.wg)(),(0,a.iD)("ul",o,[(0,a._)("li",null,(0,r.zw)(e.t("menu.home")),1),(0,a._)("li",null,(0,r.zw)(e.current),1)])}var l=n(35213),c=(0,a.aZ)({name:"Breadcrumb",props:["current"],setup(){const{t:e}=(0,l.QT)();return{t:e}}}),i=n(40089);const u=(0,i.Z)(c,[["render",s],["__scopeId","data-v-64013c38"]]);var d=u},11690:function(e,t,n){n.r(t),n.d(t,{default:function(){return E}});var a=n(73396),r=n(87139);const o={class:"flex flex-col"},s={class:"post-header"},l={key:0,class:"post-title text-white uppercase"},c={class:"main-grid"},i={class:"relative"},u=["innerHTML"],d={key:1,class:"bg-ob-deep-800 px-14 py-16 rounded-2xl shadow-xl block min-h-screen"},m=(0,a._)("br",null,null,-1),h=(0,a._)("br",null,null,-1),p=(0,a._)("br",null,null,-1);function g(e,t,n,g,b,v){const f=(0,a.up)("Breadcrumb"),w=(0,a.up)("ob-skeleton");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a.Wm)(f,{style:{"font-size":"22px"},current:e.t("menu.about")},null,8,["current"]),(0,a._)("div",o,[(0,a._)("div",s,[e.about?((0,a.wg)(),(0,a.iD)("h1",l,(0,r.zw)(e.t("titles.about")),1)):((0,a.wg)(),(0,a.j4)(w,{key:1,class:"post-title text-white uppercase",width:"100%",height:"clamp(1.2rem, calc(1rem + 3.5vw), 4rem)"}))]),(0,a._)("div",c,[(0,a._)("div",i,[e.about?((0,a.wg)(),(0,a.iD)("div",{key:0,class:"post-html",ref:"postRef",innerHTML:e.about},null,8,u)):((0,a.wg)(),(0,a.iD)("div",d,[(0,a.Wm)(w,{tag:"div",count:1,height:"36px",width:"150px",class:"mb-6"}),m,(0,a.Wm)(w,{tag:"div",count:35,height:"16px",width:"100px",class:"mr-2"}),h,p,(0,a.Wm)(w,{tag:"div",count:25,height:"16px",width:"100px",class:"mr-2"})]))])])])])}var b=n(44870),v=n(47919),f=n(35213),w=n(4922),x=n(45466),Z=n(17437),k=n(62069),y=n(50913),_=n(23925),C=n(43774),D=n.n(C),M=n(2549),R=n.n(M),z=(n(79525),n(33731),n(77372),n(61678),n(3275),n(28781),n(51973),n(61739),n(70714),n(8072),n(9688),n(64121),n(80077),n(23456),n(73286)),S=n(13184),T=n(36883),B=n(69622),H=(0,a.aZ)({name:"About",components:{Breadcrumb:v.Z,Sidebar:w.YE,Profile:w.NZ,Navigator:w.C3,Sticky:k.Z,SubTitle:y.D,Comment:_.s},setup(){const e=(0,x.E)(),t=(0,Z.j)(),{t:n}=(0,f.QT)(),r=(0,b.iH)(),o=(0,b.qj)({about:"",comments:[],haveMore:!1,isReload:!1,images:[]}),s=(0,b.qj)({current:1,size:7});t.type=3,(0,a.bv)((()=>{u(),i()})),(0,a.Ah)((()=>{e.resetHeaderImage(),D().destroy()})),(0,a.JJ)("comments",(0,a.Fl)((()=>o.comments))),(0,a.JJ)("haveMore",(0,a.Fl)((()=>o.haveMore))),S.Z.on("aboutFetchComment",(()=>{s.current=1,o.isReload=!0,u()})),S.Z.on("aboutFetchReplies",(e=>{d(e)})),S.Z.on("aboutLoadMore",(()=>{u()}));const l=e=>{(0,T.e)({images:o.images,index:o.images.indexOf(e)})},c=()=>{let e=r.value.children;if(e.length)for(let a=0;a<e.length;a++){let t=e[a],n=/^H[1-4]{1}$/;n.exec(t.tagName)&&(t.id=a)}D().init({tocSelector:"#toc2",contentSelector:".post-html",headingSelector:"h1, h2, h3",onClick:function(e){e.preventDefault()}});const t=r.value.getElementsByTagName("img");for(var n=0;n<t.length;n++)o.images.push(t[n].src),t[n].addEventListener("click",(function(e){l(e.target.currentSrc)}))},i=()=>{z.Z.getAbout().then((({data:e})=>{e.data.content=(0,B.Z)(e.data.content),o.about=e.data.content,(0,a.Y3)((()=>{R().highlightAll(),c()}))}))},u=()=>{const e={type:3,topicId:null,current:s.current,size:s.size};z.Z.getComments(e).then((({data:e})=>{o.isReload?(o.comments=e.data.records,o.isReload=!1):o.comments.push(...e.data.records),e.data.count<=o.comments.length?o.haveMore=!1:o.haveMore=!0,s.current++}))},d=e=>{z.Z.getRepliesByCommentId(o.comments[e].id).then((({data:t})=>{o.comments[e].replyDTOs=t.data}))};return{postRef:r,...(0,b.BK)(o),t:n}}}),j=n(40089);const A=(0,j.Z)(H,[["render",g]]);var E=A}}]);